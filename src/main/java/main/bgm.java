package main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class bgm {
    static Clip clip;
    static URL[] bgmURL = new URL[30];
    public bgm(){
        bgmURL[2]=getClass().getResource("/assets/sound/AI_Test_Kitchen_soothing_instrumental_music_to_help_me_focus.wav");
        bgmURL[3]=getClass().getResource("/assets/sound/AI_Test_Kitchen_intense_rock_battle.wav");
        bgmURL[4]=getClass().getResource("/assets/sound/AI_Test_Kitchen_intense_battle_theme.wav");
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
