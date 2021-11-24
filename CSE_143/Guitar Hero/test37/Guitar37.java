
public class Guitar37 implements Guitar {
   private GuitarString[] strings;
   private int time = -1;

   public static final String KEYBOARD =
           "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout

   public Guitar37() {
      strings = new GuitarString[37];
      for (int i = 0; i < 37; i++) {
         strings[i] = new GuitarString(440.0 * Math.pow(2, (i - 24.0) / 12.0));
      }
   }

   public void playNote(int pitch) {
      if (pitch >= -24 && pitch <= 12) {
         strings[pitch + 24].pluck();
      }
   }

   public boolean hasString(char key) {
      return KEYBOARD.contains(key + "");
   }

   public void pluck(char string) {
      checkArgument(string);
      strings[KEYBOARD.indexOf(string)].pluck();
   }

   public double sample() {
      double sample = 0.0;
      for (int i = 0; i < strings.length; i++) {
         sample += strings[i].sample();
      }
      return sample;
   }

   public void tic() {
      for (int i = 0; i < strings.length; i++) {
         strings[i].tic();
      }
      time++;
   }

   public int time() {
      return time + 1;
   }

   private void checkArgument (char key) {
      if (!KEYBOARD.contains(key + "")) {
         throw new IllegalArgumentException("key:" + key);
      }
   }
}
