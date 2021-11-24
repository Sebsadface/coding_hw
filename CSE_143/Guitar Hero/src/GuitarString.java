sd// Lefei (Sebastian) Liu
// 04/15/2021
// CSE143
// TA: Randair Porter
// Homework 2 (Guitar Hero)
//
// Class GuitarString can be used to simulate a guitar string with given frequency

import java.util.*;


public class GuitarString {
    private Queue<Double> ringBuffer; // a ring buffer stores a guitar string's displacement

    public static final double ENERGY_DECAY_FACTOR = 0.996;

    // pre : frequency > 0 && (StdAudio.SAMPLE_RATE / frequency) >= 2
    //       (throws illegalArgumentException if not)
    // post: constructs a guitar string of the given frequency
    public GuitarString(double frequency) {
        checkArgument(frequency);
        initializeRingBuffer(frequency);
    }

    // pre : init.length >= 2 (throws illegalArgumentException if not)
    // post: constructs a guitar string and initialize the ring buffer with given array
    public GuitarString(double[] init) {
        checkArgument(init.length);
        initializeRingBuffer(init);
    }

    // post: replaces the N elements in the ring buffer with N random values (-0.5 <= value < 0.5)
    public void pluck() {
        Random r = new Random();
        for (int i = 0; i < ringBuffer.size(); i++) {
            ringBuffer.remove();
            ringBuffer.add(r.nextDouble() - 0.5);
        }
    }

    // post: apply the Karplus-Strong update once
    public void tic() {
        ringBuffer.add((ringBuffer.remove() + ringBuffer.peek()) / 2 * ENERGY_DECAY_FACTOR);
    }

    // post: returns the current sample
    public double sample() {
        return ringBuffer.peek();
    }

    // post: initializes the ring buffer with given frequency
    private void initializeRingBuffer(double frequency) {
        ringBuffer = new LinkedList<>();
        for (int i = 0; i < getCapacity(frequency); i++) {
            ringBuffer.add(0.0);
        }
    }

    // post: initializes the ring buffer values in the given array
    private void initializeRingBuffer(double[] init) {
        ringBuffer = new LinkedList<>();
        for (double value : init) {
            ringBuffer.add(value);
        }
    }

    // post: checks whether frequency > 0 && (StdAudio.SAMPLE_RATE / frequency) >= 2
    //       throws illegalArgumentException if not
    private void checkArgument(double frequency) {
        int capacity = getCapacity(frequency);
        if (frequency <= 0 || capacity < 2) {
            throw new IllegalArgumentException("frequency: " + frequency +
                    " capacity: " + capacity);
        }
    }

    // post: checks whether capacity >= 2
    //       throws illegalArgumentException if not
    private void checkArgument(int capacity) {
        if (capacity < 2) {
            throw new IllegalArgumentException("Capacity: " + capacity);
        }
    }

    // post: returns the value of capacity (ring buffer size) with given frequency
    private int getCapacity(double frequency) {
        return (int)Math.round(StdAudio.SAMPLE_RATE / frequency);
    }
}
