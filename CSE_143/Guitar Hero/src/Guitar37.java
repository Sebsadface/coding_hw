// Lefei (Sebastian) Liu
// 04/15/2021
// CSE143
// TA: Randair Porter
// Homework 2 (Guitar Hero)
//
// Class Guitar37 can be used to simulate a guitar which has 37 unique pitches

public class Guitar37 implements Guitar {
   private GuitarString[] strings; // an array of 37 guitar strings;
   private int time = -1; // the current time

   public static final String KEYBOARD =
           "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout

   // post: constructs a guitar with 37 strings
   public Guitar37() {
      strings = new GuitarString[37];
      for (int i = 0; i < 37; i++) {
         strings[i] = new GuitarString(440.0 * Math.pow(2, (i - 24.0) / 12.0));
      }
   }

   // post: plays the note of a given pitch
   //       ignores the pitch if the pitch is not supported
   public void playNote(int pitch) {
      if (pitch >= -24 && pitch <= 12) {
         strings[pitch + 24].pluck();
      }
   }

   // post: returns true if the keyboard has the given key,
   //       false otherwise
   public boolean hasString(char key) {
      return KEYBOARD.contains(key + "");
   }

   // pre: KEYBOARD.contains(string)
   //      (throws IllegalArgumentException if not)
   // post: plucks the given string
   public void pluck(char string) {
      checkArgument(string);
      strings[KEYBOARD.indexOf(string)].pluck();
   }

   // post: returns the sum of 37 samples
   public double sample() {
      double sample = 0.0;
      for (GuitarString string : strings) {
         sample += string.sample();
      }
      return sample;
   }

   // post: apply the Karplus-Strong update to 37 strings
   public void tic() {
      for (GuitarString string : strings) {
         string.tic();
      }
      time++;
   }

   // post: returns the current time (the number of times tic has been called)
   public int time() {
      return time + 1;
   }

   // post: checks whether KEYBOARD.contains(string)
   //       throws IllegalArgumentException if not
   private void checkArgument (char string) {
      if (!KEYBOARD.contains(string + "")) {
         throw new IllegalArgumentException("string:" + string);
      }
   }
}
