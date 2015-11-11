import PIL
import Image
import numpy
import ImageOps
import sys
sys.path.append("./")
import libfann
from skimage import morphology

# ouvre une image renvoi un tableau a 2 dimension de la taille de l'image       

def OpenImage(path):
        img = Image.open(str(path))
        #im = Check(img)
        img1 = ImageOps.grayscale(img)
        largeur, hauteur = img1.size
        imdata = img1.getdata()
        tab = numpy.array(imdata)
        matrix = numpy.reshape(tab,(hauteur,largeur))
        return matrix

def FindBlock(img):
    x = 0
    y = 0
    line = list()
    col = list()
    while(x < img.shape[0]):
        y = 0
        color = 0
        while(y < img.shape[1]):
            if (img[x][y] != 255):
                color = 1
            y = y + 1
        if(color == 0):
            line.append(x)
        x = x + 1
    x = 0
    y = 0
    while(y < img.shape[1]):
        x = 0
        color = 0
        while(x < img.shape[0]):
            if(img[x][y] != 255):
                color = 1
            x = x + 1
        if(color == 0):
            col.append(y)
        y = y + 1 
    name = SelectBlock(line, col, img)
    return(name)

def SelectBlock(line, col, img):
        print('11111')
        x = 1
        y = 1
        count = 0
        while(x < len(line)):
                y = 1
                while(y < len(col)):
                        count = count + 1
                        block = list()
                        block.append(line[x])
                        block.append(col[y])
                        block.append(line[x - 1])
                        block.append(col[y - 1])
                        name = DrawBlock(block, img, count)
                        y = y + 1
                x = x + 1
        return(name)

def DrawBlock(block, img, count):
        hteur = block[0] - block[2]
        lrgeur = block[1] - block[3]
        tab = numpy.zeros((hteur, lrgeur))
        tx = 0
        ty = 0
        x = block[2]
        y = block[3]
        if(hteur * lrgeur <= 40):
                return(0)
        while(x < block[0]):
                ty = 0
                y = block[3]
                while(y < block[1]):
                        tab[tx][ty] = img[x][y]
                        print(img[x][y])
                        ty = ty + 1
                        y = y + 1
                x = x + 1
                tx = tx + 1
        name = CreateName(count)
        print(tab)
        RebuildImg(tab, name)
        Resize(name)
        im = BlackNWhite(name)
        im = Skelletonize(im)
        im = NewBuild(im)
        im = RebuildImg(im, name)
        return(name)
 
def CreateName(count):
        name = "file_" + str(count) + ".png"
        return(name)
def RebuildImg(data,path):
        Copie = Image.new("L",(data.shape[1],data.shape[0]))
        Copie.putdata(list(data.flat))
        Copie.save(fp=str(path))

def Resize(path):
        img = Image.open(path)
        size = 50, 50
        img = img.resize(size, Image.ANTIALIAS)
        img.save(path)
        img.save("toto.png")
def Seuillage(img,seuil_B,seuil_H):
        imgF=list(img.flat)
        Threshold=[]
        for i in range(len(imgF)):
                if imgF[i] <= seuil_H and imgF[i] >= seuil_B:
                        Threshold.append(0)
                else: Threshold.append(1)
        Threshold=numpy.array(Threshold)
        Threshold=Threshold.reshape(img.shape[0],img.shape[1])
        RebuildImg(Threshold*255,"test.bmp")
        return Threshold*255
def BlackNWhite(path):
        x = 0
        y = 0
        im = OpenImage(path)
        im = Seuillage(im, 0,200)
        while(x < im.shape[0]):
                y = 0
                while(y < im.shape[1]):
                        if (im[x][y] == 255):
                                im[x][y] = 0
                        else:
                                print(im[x][y])
                                im[x][y] = 1
                        y = y + 1 
                x = x + 1
        #im = numpy.array(im, dtype=numpy.uint8, ndmin=2)
        return(im)

def NewBuild(im):
        data = numpy.zeros((im.shape[0],im.shape[1]))
        x = 0
        y = 1
        while(x < im.shape[0]):
                y = 0
                while (y < im.shape[1]):
                        if(im[x][y] == False or im[x][y] == 0):
                                data[x][y] = 255
                        else:
                                data[x][y] = 0
                        y = y + 1 
                x = x + 1
        print(repr(data))
        return(data)

def Skelletonize(img):
        im = morphology.skeletonize(img)
        RebuildImg(im, "test2.png")
        return(im)

def Check(img):
        try:
	        exif = {
		        PIL.ExifTags.TAGS[k]: v
		        for k, v in img._getexif().items()
		        if k in PIL.ExifTags.TAGS
		}
        except:
	        exif = img._getexif()
        print(exif)
        if exif == None:
	        return(img)

        orientation = exif['Orientation']

        if orientation == 2:
	        img = img.transpose(Image.FLIP_LEFT_RIGHT)
        elif orientation == 3:
	        img = img.transpose(Image.ROTATE_180)
        elif orientation == 4:
	        img = img.transpose(Image.FLIP_TOP_BOTTOM)
        elif orientation == 5:
	        img = img.transpose(Image.ROTATE_90)
	        img = img.transpose(Image.FLIP_LEFT_RIGHT)
        elif orientation == 6:
	        img = img.transpose(Image.ROTATE_270)
        elif orientation == 7:
	        img = img.transpose(Image.ROTATE_270)
	        img = img.transpose(Image.FLIP_LEFT_RIGHT)
        elif orientation == 8:
	        img = img.transpose(Image.ROTATE_90)
        return(img)

def DoAll(path):
        test = OpenImage(path)
        test = Seuillage(test, 0, 200)
        name = FindBlock(test)
        return(name)
