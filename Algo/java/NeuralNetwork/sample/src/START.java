import generator.TrainingFileGenerator;
import ia.AI;
import training.NeuralNetwork;

/**
 * Created by home on 02/11/2015.
 */
public class START {

    public static String ALAPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789&'@`\\^:,$=!>-{([<#%|+.?\"})];/*~ _";
    public static String NEURAL_MAP_PATH = "./NeuralMaps/";

    public static int OUTPUT_NUMBER = ALAPHABET.length();

    public static int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        String path = generate();
        //train(path, null);
        check(NEURAL_MAP_PATH + "NeuralMap-9.283573E-6.txt");
    }

    private static void check(String path) {
        AI nn = new AI (path, ALAPHABET);

        System.out.print(nn.check("input/dataset/alphabet/c.png"));
        System.out.print(nn.check("input/dataset/alphabet/e_small.png"));
        System.out.print(nn.check("input/dataset/alphabet/c_small.png"));
        System.out.print(nn.check("input/dataset/alphabet/i_small.png"));
        System.out.print(nn.check("input/dataset/alphabet/sym_space.png"));
        System.out.print(nn.check("input/dataset/alphabet/e.png"));
        System.out.print(nn.check("input/dataset/alphabet/s_small.png"));
        System.out.print(nn.check("input/dataset/alphabet/t_small.png"));
        System.out.print(nn.check("input/dataset/alphabet/sym_space.png"));
        System.out.print(nn.check("input/dataset/alphabet/u.png"));
        System.out.print(nn.check("input/dataset/alphabet/n_small.png"));
        System.out.print(nn.check("input/dataset/alphabet/sym_space.png"));
        System.out.print(nn.check("input/dataset/alphabet/t.png"));
        System.out.print(nn.check("input/dataset/alphabet/e.png"));
        System.out.print(nn.check("input/dataset/alphabet/s.png"));
        System.out.print(nn.check("input/dataset/alphabet/t.png"));
        System.out.print(nn.check("input/dataset/alphabet/sym_space.png"));
        System.out.print(nn.check("input/dataset/alphabet/sym_arob.png"));
        System.out.print(nn.check("input/dataset/alphabet/sym_num.png"));
        System.out.print(nn.check("input/dataset/alphabet/num_7.png"));

    }

    private static void train(String trainingFilePath, String neuralMapPath) {
        float desiredError = 0.00001f;
        int maxEpoch = 1000000;
        int verboseStep = 100;

        NeuralNetwork nn = null;
        if (neuralMapPath == null) {
            nn = new NeuralNetwork(IMAGE_SIZE * IMAGE_SIZE, OUTPUT_NUMBER);
            nn.train(trainingFilePath, maxEpoch, verboseStep, desiredError, NEURAL_MAP_PATH);
        } else {
            nn = new NeuralNetwork(neuralMapPath);
            nn.train(trainingFilePath, maxEpoch, verboseStep, desiredError, NEURAL_MAP_PATH);
        }
    }


    private static String generate() {
        TrainingFileGenerator TFG = new TrainingFileGenerator(ALAPHABET);

        String path = "input/dataset/alphabet/";

        TFG.addFile('A', path + "a.png");
        TFG.addFile('B', path + "b.png");
        TFG.addFile('C', path + "c.png");
        TFG.addFile('D', path + "d.png");
        TFG.addFile('E', path + "e.png");
        TFG.addFile('F', path + "f.png");
        TFG.addFile('G', path + "g.png");
        TFG.addFile('H', path + "h.png");
        TFG.addFile('I', path + "i.png");
        TFG.addFile('J', path + "j.png");
        TFG.addFile('K', path + "k.png");
        TFG.addFile('L', path + "l.png");
        TFG.addFile('M', path + "m.png");
        TFG.addFile('N', path + "n.png");
        TFG.addFile('O', path + "o.png");
        TFG.addFile('P', path + "p.png");
        TFG.addFile('Q', path + "q.png");
        TFG.addFile('R', path + "r.png");
        TFG.addFile('S', path + "s.png");
        TFG.addFile('T', path + "t.png");
        TFG.addFile('U', path + "u.png");
        TFG.addFile('V', path + "v.png");
        TFG.addFile('W', path + "w.png");
        TFG.addFile('X', path + "x.png");
        TFG.addFile('Y', path + "y.png");
        TFG.addFile('Z', path + "z.png");

        TFG.addFile('a', path + "a_small.png");
        TFG.addFile('b', path + "b_small.png");
        TFG.addFile('c', path + "c_small.png");
        TFG.addFile('d', path + "d_small.png");
        TFG.addFile('e', path + "e_small.png");
        TFG.addFile('f', path + "f_small.png");
        TFG.addFile('g', path + "g_small.png");
        TFG.addFile('h', path + "h_small.png");
        TFG.addFile('i', path + "i_small.png");
        TFG.addFile('j', path + "j_small.png");
        TFG.addFile('k', path + "k_small.png");
        TFG.addFile('l', path + "l_small.png");
        TFG.addFile('m', path + "m_small.png");
        TFG.addFile('n', path + "n_small.png");
        TFG.addFile('o', path + "o_small.png");
        TFG.addFile('p', path + "p_small.png");
        TFG.addFile('q', path + "q_small.png");
        TFG.addFile('r', path + "r_small.png");
        TFG.addFile('s', path + "s_small.png");
        TFG.addFile('t', path + "t_small.png");
        TFG.addFile('u', path + "u_small.png");
        TFG.addFile('v', path + "v_small.png");
        TFG.addFile('w', path + "w_small.png");
        TFG.addFile('x', path + "x_small.png");
        TFG.addFile('y', path + "y_small.png");
        TFG.addFile('z', path + "z_small.png");

        TFG.addFile('0', path + "num_0.png");
        TFG.addFile('1', path + "num_1.png");
        TFG.addFile('2', path + "num_2.png");
        TFG.addFile('3', path + "num_3.png");
        TFG.addFile('4', path + "num_4.png");
        TFG.addFile('5', path + "num_5.png");
        TFG.addFile('z', path + "num_6.png");
        TFG.addFile('7', path + "num_7.png");
        TFG.addFile('8', path + "num_8.png");
        TFG.addFile('9', path + "num_9.png");

        TFG.addFile('&', path + "sym_amper.png");
        TFG.addFile('\'', path + "sym_apos.png");
        TFG.addFile('@', path + "sym_arob.png");
        TFG.addFile('`', path + "sym_bquote.png");
        TFG.addFile('\\', path + "sym_bslash.png");
        TFG.addFile('^', path + "sym_caret.png");
        TFG.addFile(':', path + "sym_colon.png");
        TFG.addFile(',', path + "sym_comma.png");
        TFG.addFile('$', path + "sym_dollar.png");
        TFG.addFile('=', path + "sym_equal.png");
        TFG.addFile('!', path + "sym_exclmark.png");
        TFG.addFile('>', path + "sym_gthan.png");
        TFG.addFile('-', path + "sym_hyphen.png");
        TFG.addFile('{', path + "sym_lcbracket.png");
        TFG.addFile('(', path + "sym_lparen.png");
        TFG.addFile('[', path + "sym_lsqbracket.png");
        TFG.addFile('<', path + "sym_lthan.png");
        TFG.addFile('#', path + "sym_num.png");
        TFG.addFile('%', path + "sym_pcent.png");
        TFG.addFile('|', path + "sym_pipe.png");
        TFG.addFile('+', path + "sym_plus.png");
        TFG.addFile('.', path + "sym_point.png");
        TFG.addFile('?', path + "sym_questmark.png");
        TFG.addFile('"', path + "sym_quotmark.png");
        TFG.addFile('}', path + "sym_rcbracket.png");
        TFG.addFile(')', path + "sym_rparen.png");
        TFG.addFile(']', path + "sym_rsqbracket.png");
        TFG.addFile(';', path + "sym_scolon.png");
        TFG.addFile('/', path + "sym_slash.png");
        TFG.addFile(' ', path + "sym_space.png");
        TFG.addFile('*', path + "sym_star.png");
        TFG.addFile('~', path + "sym_tilde.png");
        TFG.addFile('_', path + "sym_under.png");

        String file_path = TFG.generateFile();

        if (file_path == null) {
            System.exit(-1);
        }
        return file_path;
    }
}
