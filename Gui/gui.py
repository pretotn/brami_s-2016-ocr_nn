import sys, os
# import cv
from PIL import Image
# import CameraClass as cam
from PyQt4 import QtGui, QtCore, Qt
# i = 0

class OcrGui(QtGui.QWidget):
	"""docstring for OcrGui"""
	def __init__(self, parent=None):
		super(OcrGui, self).__init__(parent)

		self.fd = ""
		self.tmpFileName = "tmpResult.bmp"
		# self.realTimer = QtCore.QTimer(self)
		# self.realTimer.timeout.connect(self.makeRealTime)
		# self.realTimer.setInterval(2000)
		# self.realTimer.stop()

		# self.indexReal = 0

		self.initUI()
	
	def initUI(self):
		self.setStyleSheet("background-color: white")
		self.setWindowTitle("OCR")

		self.openFileDialogButton = QtGui.QPushButton("Use an existing file")
		self.openFileDialogButton.clicked.connect(self.openFD)

		# self.cameraDevice = cam.CameraDevice()
		# self.cameraWidget = cam.CameraWidget(self.cameraDevice)

		# self.takePictureButton = QtGui.QPushButton("Take a picture now")
		# self.takePictureButton.clicked.connect(self.takePictureAction)


		self.thumbnail = QtGui.QImage()

		self.thumbnailLbl = QtGui.QLabel()
		# self.thumbnailLbl.setAlignment(QtCore.Qt.AlignTop)
		self.thumbnailLbl.setMinimumSize(250, 250)

		self.confirmButton = QtGui.QPushButton("Confirm")
		self.confirmButton.setStyleSheet("margin-top: 10px; background-color: #27ae60; color: white")
		self.confirmButton.clicked.connect(self.sendToAlgo)

		# self.realTimeCheckBox = QtGui.QCheckBox("Enable realtime")
		# self.realTimeCheckBox.stateChanged.connect(self.checkRealTime)

		# self.treatmentMode = QtGui.QGroupBox("Choose the mode")
		# self.treatmentMode.setStyleSheet("border-top: 1px solid black;border-bottom: 1px solid black")
		# self.normalMode = QtGui.QRadioButton("Normal")
		# self.normalMode.setChecked(True)
		# self.advancedMode = QtGui.QRadioButton("Advanced")
		# self.modeHLayout = QtGui.QHBoxLayout()
		# self.modeHLayout.addWidget(self.normalMode)
		# self.modeHLayout.addWidget(self.advancedMode)
		# self.treatmentMode.setLayout(self.modeHLayout)

		self.resultText = QtGui.QTextEdit("No data available")
		self.resultText.setReadOnly(True)
		self.resultText.setLineWrapMode(QtGui.QTextEdit.FixedPixelWidth)
		self.resultText.setLineWrapColumnOrWidth(200)
		self.resultText.setFixedHeight(200)
		# self.resultText.setAlignment(QtCore.Qt.AlignBottom)

		self.mainHLayout = QtGui.QHBoxLayout()

		self.firstVLayout = QtGui.QVBoxLayout()
		# self.firstVLayout.addWidget(self.cameraWidget)
		# self.firstVLayout.addWidget(self.takePictureButton)
		self.firstVLayout.addWidget(self.openFileDialogButton)
		self.firstVLayout.addWidget(self.confirmButton)
		
		self.secondVLayout = QtGui.QVBoxLayout()
		self.secondVLayout.addWidget(self.thumbnailLbl)
		# self.secondVLayout.addWidget(self.realTimeCheckBox)
		# self.secondVLayout.addWidget(self.treatmentMode)
		self.secondVLayout.addWidget(self.resultText)

		self.mainHLayout.addLayout(self.firstVLayout)
		self.mainHLayout.addLayout(self.secondVLayout)

		self.setLayout(self.mainHLayout)
		self.show()

	def sendToAlgo(self, fd=0):
		useFd = ""
		mode = True

		print self.thumbnailLbl.text()
		if fd == 0:
			useFd = self.fd
		else:
			useFd = fd
		# if self.advancedMode.isChecked() and self.normalMode.isChecked() == False:
		# 	mode = False
		# algoResult = runalgo(useFd, mode) -> usefd est le filename complet du fichier; mode est le mode normal (true) ou avance (false)
		# if algoResult == Null: -> ou ""
		#	algoResult = "No data available"
		# self.resultText.setText(algoResult)

		# print "algo", useFd

	def setAllButtonEnable(self, status):
		# self.takePictureButton.setEnabled(status)
		self.openFileDialogButton.setEnabled(status)
		self.confirmButton.setEnabled(status)

	# def checkRealTime(self, state):
	# 	if state == QtCore.Qt.Checked:
	# 		self.setAllButtonEnable(False)
	# 		self.realTimer.start()
	# 	else:
	# 		self.setAllButtonEnable(True)
	# 		self.realTimer.stop()
	# 		self.indexReal = 0

	# def makeRealTime(self):
	# 	self.cameraDevice.stopTimer()
	# 	frame = cv.CloneImage(self.cameraWidget._frame)
	# 	filename = os.path.join(os.path.abspath("./realtime"), "realTimePic" + str(self.indexReal) + ".bmp")
	# 	cv.SaveImage(filename, frame)
	# 	self.sendToAlgo(filename)
	# 	self.indexReal += 1
	# 	self.loadThumbnail(filename)
	# 	self.cameraDevice.startTimer()

	def loadThumbnail(self, fd):
		self.thumbnail.load(fd)
		self.thumbnail = self.thumbnail.scaled(300, 300, QtCore.Qt.KeepAspectRatio, QtCore.Qt.SmoothTransformation)
		self.thumbnailLbl.setPixmap(QtGui.QPixmap(self.thumbnail))
		self.thumbnailLbl.show()

	def openFD(self):
		# self.cameraDevice.stopTimer()
		fd = str(QtGui.QFileDialog().getOpenFileName())

		# verification du fichier

		print type(fd)
		msg = ""
		if os.path.isfile(fd) == False:
			msg += "The file doesn't exist\n"
		f, ext = os.path.splitext(fd)
		if ext not in [".jpg", ".jpeg", ".png", ".bmp"]:
			msg += "The file doesn't have good extension/format (bmp/jpg/jpeg/png)\n"
		if msg != "":
			QtGui.QMessageBox.about(self, "My message box", msg)
			return self.openFD()

		self.fd = fd
		# self.cameraDevice.startTimer()
		self.loadThumbnail(fd)


	# def writeOnFrame(self, frame, msg, pos):
	# 	font = cv.InitFont(cv.CV_FONT_HERSHEY_DUPLEX, 3.0, 10.0, thickness=5)
	# 	cv.PutText(frame, msg, pos, font, cv.RGB(0, 0, 0))
	# 	self.cameraWidget._onNewFrame(frame)

	# def takePictureAction(self):
	# 	self.takePictureButton.setEnabled(False)
	# 	self.openFileDialogButton.setEnabled(False)
	# 	self.timering = 3
	# 	timer = QtCore.QTimer(self)
		
	# 	def count():
	# 		if self.timering >= 0:
	# 			self.cameraDevice.stopTimer()
	# 			frame = cv.CloneImage(self.cameraWidget._frame)
	# 			msg = str(self.timering)
	# 			self.writeOnFrame(frame, msg, (200, 300))
	# 			self.timering -= 1
	# 			self.cameraDevice.startTimer()
	# 		else:
	# 			timer.stop()
	# 			self.cameraDevice.stopTimer()
	# 			frame = cv.CloneImage(self.cameraWidget._frame)
	# 			cv.SaveImage(self.tmpFileName, frame)
	# 			self.fd = os.path.join(os.getcwd(), self.tmpFileName)
	# 			self.loadThumbnail(self.fd)
	# 			self.cameraDevice.startTimer()
	# 			self.takePictureButton.setEnabled(True)
	# 			self.openFileDialogButton.setEnabled(True)

	# 	timer.timeout.connect(count)
	# 	timer.setInterval(1000)
	# 	timer.start()


def main():
	app = QtGui.QApplication(sys.argv)

	ocr = OcrGui()

	sys.exit(app.exec_())

if __name__ == '__main__':
	main()