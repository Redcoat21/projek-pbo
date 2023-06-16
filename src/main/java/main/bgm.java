package main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class bgm {
    static Clip clip;
    static URL[] bgmURL = new URL[30];
    public bgm(){
        bgmURL[4]=getClass().getResource("/assets/sound/AI_Test_Kitchen_soothing_instrumental_music_to_help_me_focus.wav");
        bgmURL[2]=getClass().getResource("/assets/sound/23. Accolade - Intro.wav");
        bgmURL[1]=getClass().getResource("/assets/sound/AI_Test_Kitchen_dark_loopable.wav");
        bgmURL[3]=getClass().getResource("/assets/sound/04. Awakening.wav");
    }
    public static void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(bgmURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e){

        }
    }
    public static void play(){
        clip.start();
    }
    public static void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void stop(){
        clip.stop();
    }
}
