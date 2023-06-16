package main;
import entities.Movable;

import java.io.File;
import java.io.InputStream;
import javax.sound.sampled.*;
public class bgm {
    protected Map map;

    {
        InputStream music;
        try{
            File file = new File("AI_Test_Kitchen_dark_loopable.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            //clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            //if(map.getMap() instanceof Movable){

           // }
        }catch (Exception e){
            System.out.println("error");
        }

    }

}
