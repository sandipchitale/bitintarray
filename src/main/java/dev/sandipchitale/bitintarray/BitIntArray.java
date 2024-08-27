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
        bitIntArray = new int[this.size / Integer.SIZE + 1]; // to include the last stretch
    }

    public int getSize() {
        return size; // return the size of the array
    }

    /**
     * Set the bit at the given index.
     *
     * @param index the zero-based index of the bit to set
     */
    public void set(int index) {
        _set(index, true);
    }

    /**
     * Unset the bit at the given index.
     *
     * @param index the zero-based index of the bit to unset
     */
    public void unset(int index) {
        _set(index, false);
    }

    /**
     * Flip the bit at the given index.
     *
     * @param index the zero-based index of the bit to flip
     * @return the new value of the bit
     */
    public boolean flip(int index) {
        _set(index, !isSet(index));
        return isSet(index);
    }

    private void _set(int index, boolean value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
        int intIndex = index / Integer.SIZE;
        int bitIndex = Integer.SIZE - (index % Integer.SIZE) - 1; // 0-based index from left
        if (value) {
            bitIntArray[intIndex] |= 1 << bitIndex;
        } else {
            bitIntArray[intIndex] &= ~(1 << bitIndex);
        }
    }

    /**
     * Check if the bit at the given index is set.
     *
     * @param index the zero-based index of the bit to check
     * @return true if the bit is set, false otherwise
     */
    public boolean isSet(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
        int intIndex = index / Integer.SIZE;
        int bitIndex = Integer.SIZE - (index % Integer.SIZE) - 1; // 0-based index from left
        return (bitIntArray[intIndex] & (1 << bitIndex)) != 0;
    }

    /**
     * Return a string representation of the bits in the array.
     *
     * @return a string representation of the bits in the array
     */
    public String allBits() {
        return allBits(false);
    }

    /**
     * Return a string representation of the bits in the array.
     *
     * @param indicateBits if true, indicate the bits that are set with a caret (^) in the next line
     * @return a string representation of the bits in the array
     */
    public String allBits(boolean indicateBits) {
        StringBuilder bits = new StringBuilder();
        for (int i = 0; i < bitIntArray.length; i++) {
            boolean last = i == bitIntArray.length - 1;
            bits.append(toBinaryString(bitIntArray[i], (last ? (size % Integer.SIZE) : Integer.SIZE)));
            if (!last) {
                bits.append(" "); // separate with a space
            }
        }
        String bitsString = bits.toString();
        return bitsString + (indicateBits ? "\n" + bitsString.replace("0", " ").replace("1", "^") : "");
    }

    /**
     * Return the number of bits that are set.
     *
     * @return the number of bits that are set
     */
    public int setBits() {
        int count = 0;
        for (int i = 0; i < bitIntArray.length; i++) {
            if (isSet(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Return true of some number of bits are set.
     *
     * @return true of some number of bits are set, else false
     */
    public boolean someBitsSet() {
        return setBits() > 0;
    }

    /**
     * Return true if no bits are set.
     *
     * @return true if no bits are set, else false
     */
    public boolean noBitsSet() {
        return setBits() == 0;
    }

    /**
     * Return true if all bits are set.
     *
     * @return true if all bits are set, else false
     */
    public boolean allBitsSet() {
        return setBits() == size;
    }

    /**
     * Return a string representation of the bits stored in an int.
     * All <code>Integer.SIZE</code> bits are shown.
     *
     * @return a string representation of the bits stored in an int.
     */
    private static String toBinaryString(int i) {
        return toBinaryString(i, Integer.SIZE);
    }

    /**
     * Return a string representation of the leftmost <code>leftBits</code> bits stored in an int.
     *
     * @param i        the int to convert to binary string
     * @param leftBits the number of leftmost bits to show
     * @return a string representation of the leftmost <code>leftBits</code> bits stored in an int.
     */
    private static String toBinaryString(int i, int leftBits) {
        return String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0').substring(0, leftBits);
    }
}
