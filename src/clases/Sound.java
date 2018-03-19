/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author mac
 */
public class Sound {
    public static final AudioClip PACMAN_MUNCH = Applet.newAudioClip(Sound.class.getResource("pacman_munch.wav"));
    public static final AudioClip PACMAN_BEGINNING = Applet.newAudioClip(Sound.class.getResource("pacman_beginning.wav"));
    public static final AudioClip PACMAN_DEATH = Applet.newAudioClip(Sound.class.getResource("pacman_death.wav"));
    public static final AudioClip SIREN = Applet.newAudioClip(Sound.class.getResource("siren.wav"));
}