package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static final double CONCERT = 440.0;
    public static GuitarString[] GuitarArray = new GuitarString[keyboard.length()];

    public static void main(String[] args){
        /*initial array*/
        for(int i=0;i<GuitarArray.length;i++){
            double frequency = CONCERT * Math.pow(2,(i-24)/12.0);
            GuitarArray[i] = new GuitarString(frequency);
        }

        while(true){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                for(int i=0;i<keyboard.length();i++){
                    if(key == keyboard.indexOf(i)){
                        GuitarArray[i].pluck();
                        break;
                    }
                }
            }

            double sample = 0;
            for(int i=0;i<GuitarArray.length;i++){
                sample += GuitarArray[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(int i=0;i<GuitarArray.length;i++){
                GuitarArray[i].tic();
            }

        }

    }
}
