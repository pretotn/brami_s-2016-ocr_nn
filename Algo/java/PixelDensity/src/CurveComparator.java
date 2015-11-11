import java.util.ArrayList;

/**
 * Created by nicol on 03/10/2015.
 */
public class CurveComparator {

    private Letter mLetterA;
    private Letter mLetterB;

    public CurveComparator(Letter letter_a, Letter letter_b) {
        mLetterA = letter_a;
        mLetterB = letter_b;
    }

    private CurveAnalyserResult compareCurve(ArrayList<Integer> ori_curve, ArrayList<Integer> curve) {
        float nbError = 0;
        float totalDistError = 0;

        for (int i = 0; i < curve.size(); i++) {
            if (curve.get(i) != ori_curve.get(i)) {
                nbError++;
                totalDistError += Math.abs(curve.get(i) - ori_curve.get(i));
            }
        }

        CurveAnalyserResult result = new CurveAnalyserResult();
        result.mDistError = totalDistError;
        result.mMatch = ((curve.size() - nbError) / curve.size()) * 100.f;

        return result;
    }

    public CurveAnalyserResult getMatchValue() {
        CurveAnalyserResult result_H = compareCurve(mLetterA.getHorizontalCurve(), mLetterB.getHorizontalCurve());
        CurveAnalyserResult result_V = compareCurve(mLetterA.getVerticalCurve(), mLetterB.getVerticalCurve());

        CurveAnalyserResult result_F = new CurveAnalyserResult();
        result_F.mDistError = result_H.mDistError + result_V.mDistError;
        result_F.mMatch = (result_H.mMatch + result_V.mMatch) / 2;

        return result_F;
    }

    public class CurveAnalyserResult {
        public float mMatch;
        public float mDistError;
    }
}
