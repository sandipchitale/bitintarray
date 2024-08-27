package dev.sandipchitale.bitintarray;

/**
 * A simple implementation of a BitIntArray.
 * Creates an array of integers and allows setting, unsetting and flipping of bits using zero-based index.
 * This implementation is not thread-safe.
 */
public class BitIntArray {
    private final int size;
    private final int[] bitIntArray;

    public BitIntArray() {
        this(Integer.SIZE);
    }

    public BitIntArray(int size) {
        this.size = size;
        bitIntArray = new int[this.size / Integer.SIZE + 1];
    }

    public int getSize() {
        return size;
    }

    public boolean set(int index) {
        _set(index, true);
        return true;
    }

    public boolean unset(int index) {
        _set(index, false);
        return false;
    }

    public boolean flip(int index) {
        _set(index, !isSet(index));
        return isSet(index);
    }

    public void _set(int index, boolean value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
        int intIndex = index / Integer.SIZE;
        int bitIndex = Integer.SIZE - (index % Integer.SIZE) - 1;
        if (value) {
            bitIntArray[intIndex] |= 1 << bitIndex;
        } else {
            bitIntArray[intIndex] &= ~(1 << bitIndex);
        }
    }

    public boolean isSet(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
        int intIndex = index / Integer.SIZE;
        int bitIndex = Integer.SIZE - (index % Integer.SIZE) - 1;
        return (bitIntArray[intIndex] & (1 << bitIndex)) != 0;
    }
    public String allBits() {
        return allBits(false);
    }

    public String allBits(boolean indicateBits) {
        StringBuilder bits = new StringBuilder();
        for (int i = 0; i < bitIntArray.length; i++) {
            boolean last = i == bitIntArray.length - 1;
            bits.append(toBinaryString(bitIntArray[i], (last ? (size % Integer.SIZE) : Integer.SIZE)));
            if (!last) {
                bits.append(" ");
            }
        }
        String bitsString = bits.toString();
        return bitsString + (indicateBits ? "\n" + bitsString.replace("0", " ").replace("1", "^") : "");
    }

    private static String toBinaryString(int i) {
        return toBinaryString(i, Integer.SIZE);
    }

    private static String toBinaryString(int i, int mask) {
        return String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0').substring(0, mask);
    }
}
