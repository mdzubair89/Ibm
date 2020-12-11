package ibm.imfras_baithul_mal;

import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ObjectiveActivity extends AppCompatActivity {

    TextView textViewFirstPart;
    TextView textViewSecondPartHead;
    TextView textViewSecondPart;
    TextView textViewThirdPartHead;
    TextView textViewThirdPart;
    TextView textViewFourthPartHead;
    TextView textViewFourthPart;
    TextView textViewFifthPartHead;
    TextView textViewFifthPart;
    TextView textViewSixthPartHead;
    TextView textViewSixthPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objective);

        textViewFirstPart = (TextView) findViewById(R.id.textViewFirstPart);
        textViewSecondPartHead = (TextView) findViewById(R.id.textViewSecondPartHead);
        textViewSecondPart = (TextView) findViewById(R.id.textViewSecondPart);
        textViewThirdPartHead = (TextView) findViewById(R.id.textViewThirdPartHead);
        textViewThirdPart = (TextView) findViewById(R.id.textViewThirdPart);
        textViewFourthPartHead = (TextView) findViewById(R.id.textViewFourthPartHead);
        textViewFourthPart = (TextView) findViewById(R.id.textViewFourthPart);
        textViewFifthPartHead = (TextView) findViewById(R.id.textViewFifthPartHead);
        textViewFifthPart = (TextView) findViewById(R.id.textViewFifthPart);
        textViewSixthPartHead = (TextView) findViewById(R.id.textViewSixPartHead);
        textViewSixthPart = (TextView) findViewById(R.id.textViewSixPart);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Bamini.TTF");
        /* Hadith */
        textViewFirstPart.setText("my;yh`;tpd; ghijapy; ePq;fs; nryT nra;Aq;fs; (mt;thW ePq;fs; nryT nra;ahky;) cq;fs; iffshNyNa (cq;fis) moptpd; ghy; Nghl;Lf; nfhs;shjPHfs;. ,d;Dk; ePq;fs; ed;ik nra;Aq;fs;. epr;rakhf my;yh`; ed;ik nra;fpd;wtHfis Nerpf;fpd;whd;. \n" +
                " ----- (my; FHMd; 2:195)\n");
        /* arimugam*/
        textViewSecondPartHead.setText("mwpKfk;:-");
        textViewSecondPart.setText("K];ypk;fspd; <ifg; gz;Gfspy; kpf Kf;fpakhdJ ]jfh (jHkk;). ,jid mbg;gilahff; nfhz;L my;yh`;tpd; fpUigahy; 2012 Mk; Mz;L Nk khjk; 5-Mk; Njjp; IMFRAS ez;gHfshy; IMFRAS BAITHUL MAL (IBM) Muk;gpf;fg;gl;lJ.\n");
        /* Nokkam*/
        textViewThirdPartHead.setText("Nehf;fk;:-");
        textViewThirdPart.setText("ekJ}H Nkyg;ghisaj;jpy; epiwa mikg;Gfs; gy ey;y tp\\aq;fis nra;J tUfpd;wJ. ekJ IMFRAS BAITHUL MAL (IBM) d; Nehf;fkhdJ mbg;gil trjpaw;w Viofspd; tho;thjhuj;jpw;F ek;khy; ,ad;w cjtpfis nra;tNj MFk;. \n");
        textViewFourthPartHead.setText("gzpfs;:-");
        textViewFourthPart.setText("Mjutw;w Viof; FLk;gq;fSf;Fk;, tpjitg; ngz;fSf;Fk; khjhe;jpu cjtpj; njhif toq;Fjy;.\n NehahspfSf;F kUj;Jt cjtp.;\n Viof; FkHfSf;F jpUkz cjtp.\n Nehd;G fhyq;fspy; [f;fhj; kw;Wk; ]jfh.\n");
        textViewFifthPartHead.setText("tq;fp fzf;F:-");
        textViewFifthPart.setText("IBM tuT nryT fzf;Ffis ghh;gjw;F tq;fp fzf;F Njitg;gl;lJ ehk; rl;lg;gb hp[p];lh; nra;ahjjhy; IBM ngahpy; tq;fp fzf;F njhlq;f Kbatpy;iy Mifahy; Kfk;kJ upy;thd; vd;w jdp egh; ngahpy; Adpad; tq;fpapy; fzf;F Muk;gpf;fg;gl;lJ. IBM rk;ge;jg;gl;l gdptu;jidfis kl;Lk; ,jpy; nra;ayhk; vd;W KbT nra;ag;gl;lJ.\n");



        textViewFirstPart.setTypeface(typeface);
        textViewSecondPartHead.setTypeface(typeface);
        textViewSecondPart.setTypeface(typeface);
        textViewThirdPartHead.setTypeface(typeface);
        textViewThirdPart.setTypeface(typeface);
        textViewFourthPartHead.setTypeface(typeface);
        textViewFourthPart.setTypeface(typeface);
        textViewFifthPartHead.setTypeface(typeface);
        textViewFifthPart.setTypeface(typeface);



    }
}
