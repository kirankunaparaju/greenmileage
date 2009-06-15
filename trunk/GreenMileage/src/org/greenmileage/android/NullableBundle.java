package org.greenmileage.android;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

/**
 * A bundle that accepts and handles nulls
 * @author Connor Garvey
 * @created Jun 14, 2009 10:13:06 AM
 * @version 0.0.5
 * @since 0.0.5
 */
public class NullableBundle {
  private final Bundle bundle;
  
  /**
   * Creates a nullable bundle
   * @param bundle the bundle backing the nullable
   */
  public NullableBundle(final Bundle bundle) {
    this.bundle = bundle;
  }
  
  /**
   * @see android.os.Bundle#clear()
   */
  public void clear() {
    this.bundle.clear();
  }
  
  /**
   * @param key the key to find
   * @return true if the bundle contains the key, false otherwise
   * @see android.os.Bundle#containsKey(java.lang.String)
   */
  public boolean containsKey(final String key) {
    return this.bundle.containsKey(key);
  }
  
  /**
   * @return the content description
   * @see android.os.Bundle#describeContents()
   */
  public int describeContents() {
    return this.bundle.describeContents();
  }
  
  /**
   * @param o the object to compare
   * @return true if the object is equal to this, false otherwise
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object o) {
    return this.bundle.equals(o);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#get(java.lang.String)
   */
  public Object get(final String key) {
    return this.bundle.get(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getBoolean(java.lang.String)
   */
  public boolean getBoolean(final String key) {
    return this.bundle.getBoolean(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @param defaultValue the value to return if the key doesn't exist
   * @return the value for the specified key or the default value if it doesn't exist
   * @see android.os.Bundle#getBoolean(java.lang.String, boolean)
   */
  public boolean getBoolean(final String key, final boolean defaultValue) {
    return this.bundle.getBoolean(key, defaultValue);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getBooleanArray(java.lang.String)
   */
  public boolean[] getBooleanArray(final String key) {
    return this.bundle.getBooleanArray(key);
  }
  
  /**
   * @return the bundle
   */
  public Bundle getBundle() {
    return this.bundle;
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getBundle(java.lang.String)
   */
  public Bundle getBundle(final String key) {
    return this.bundle.getBundle(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getByte(java.lang.String)
   */
  public byte getByte(final String key) {
    return this.bundle.getByte(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @param defaultValue the value to return if the key doesn't exist
   * @return the value for the specified key or the default value if it doesn't exist
   * @see android.os.Bundle#getByte(java.lang.String, byte)
   */
  public Byte getByte(final String key, final byte defaultValue) {
    return this.bundle.getByte(key, defaultValue);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getByteArray(java.lang.String)
   */
  public byte[] getByteArray(final String key) {
    return this.bundle.getByteArray(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getChar(java.lang.String)
   */
  public char getChar(final String key) {
    return this.bundle.getChar(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @param defaultValue the value to return if the key doesn't exist
   * @return the value for the specified key or the default value if it doesn't exist
   * @see android.os.Bundle#getChar(java.lang.String, char)
   */
  public char getChar(final String key, final char defaultValue) {
    return this.bundle.getChar(key, defaultValue);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getCharArray(java.lang.String)
   */
  public char[] getCharArray(final String key) {
    return this.bundle.getCharArray(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getCharSequence(java.lang.String)
   */
  public CharSequence getCharSequence(final String key) {
    return this.bundle.getCharSequence(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getDouble(java.lang.String)
   */
  public double getDouble(final String key) {
    return this.bundle.getDouble(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @param defaultValue the value to return if the key doesn't exist
   * @return the value for the specified key or the default value if it doesn't exist
   * @see android.os.Bundle#getDouble(java.lang.String, double)
   */
  public double getDouble(final String key, final double defaultValue) {
    return this.bundle.getDouble(key, defaultValue);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getDoubleArray(java.lang.String)
   */
  public double[] getDoubleArray(final String key) {
    return this.bundle.getDoubleArray(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getFloat(java.lang.String)
   */
  public float getFloat(final String key) {
    return this.bundle.getFloat(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @param defaultValue the value to return if the key doesn't exist
   * @return the value for the specified key or the default value if it doesn't exist
   * @see android.os.Bundle#getFloat(java.lang.String, float)
   */
  public float getFloat(final String key, final float defaultValue) {
    return this.bundle.getFloat(key, defaultValue);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getFloatArray(java.lang.String)
   */
  public float[] getFloatArray(final String key) {
    return this.bundle.getFloatArray(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getInt(java.lang.String)
   */
  public int getInt(final String key) {
    return this.bundle.getInt(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @param defaultValue the value to return if the key doesn't exist
   * @return the value for the specified key or the default value if it doesn't exist
   * @see android.os.Bundle#getInt(java.lang.String, int)
   */
  public int getInt(final String key, final int defaultValue) {
    return this.bundle.getInt(key, defaultValue);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getIntArray(java.lang.String)
   */
  public int[] getIntArray(final String key) {
    return this.bundle.getIntArray(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getIntegerArrayList(java.lang.String)
   */
  public ArrayList<Integer> getIntegerArrayList(final String key) {
    return this.bundle.getIntegerArrayList(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getLong(java.lang.String)
   */
  public long getLong(final String key) {
    return this.bundle.getLong(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @param defaultValue the value to return if the key doesn't exist
   * @return the value for the specified key or the default value if it doesn't exist
   * @see android.os.Bundle#getLong(java.lang.String, long)
   */
  public long getLong(final String key, final long defaultValue) {
    return this.bundle.getLong(key, defaultValue);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getLongArray(java.lang.String)
   */
  public long[] getLongArray(final String key) {
    return this.bundle.getLongArray(key);
  }
  
  /**
   * @param <T> the type of {@link Parcelable}
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getParcelable(java.lang.String)
   */
  public <T extends Parcelable> T getParcelable(final String key) {
    return this.bundle.getParcelable(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getParcelableArray(java.lang.String)
   */
  public Parcelable[] getParcelableArray(final String key) {
    return this.bundle.getParcelableArray(key);
  }
  
  /**
   * @param <T> the type of {@link Parcelable}
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getParcelableArrayList(java.lang.String)
   */
  public <T extends Parcelable> ArrayList<T> getParcelableArrayList(final String key) {
    return this.bundle.getParcelableArrayList(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getSerializable(java.lang.String)
   */
  public Serializable getSerializable(final String key) {
    return this.bundle.getSerializable(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getShort(java.lang.String)
   */
  public short getShort(final String key) {
    return this.bundle.getShort(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @param defaultValue the value to return if the key doesn't exist
   * @return the value for the specified key or the default value if it doesn't exist
   * @see android.os.Bundle#getShort(java.lang.String, short)
   */
  public short getShort(final String key, final short defaultValue) {
    return this.bundle.getShort(key, defaultValue);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getShortArray(java.lang.String)
   */
  public short[] getShortArray(final String key) {
    return this.bundle.getShortArray(key);
  }
  
  /**
   * @param <T> the type of {@link Parcelable}
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getSparseParcelableArray(java.lang.String)
   */
  public <T extends Parcelable> SparseArray<T> getSparseParcelableArray(final String key) {
    return this.bundle.getSparseParcelableArray(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getString(java.lang.String)
   */
  public String getString(final String key) {
    return this.bundle.getString(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getStringArray(java.lang.String)
   */
  public String[] getStringArray(final String key) {
    return this.bundle.getStringArray(key);
  }
  
  /**
   * @param key the key of the item to retrieve
   * @return the value for the specified key or null if it doesn't exist
   * @see android.os.Bundle#getStringArrayList(java.lang.String)
   */
  public ArrayList<String> getStringArrayList(final String key) {
    return this.bundle.getStringArrayList(key);
  }
  
  /**
   * @return true if the bundle has file descriptors, false otherwises
   * @see android.os.Bundle#hasFileDescriptors()
   */
  public boolean hasFileDescriptors() {
    return this.bundle.hasFileDescriptors();
  }
  
  /**
   * @return the hash code
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return this.bundle.hashCode();
  }
  
  /**
   * @return true if the bundle is empty, false otherwise
   * @see android.os.Bundle#isEmpty()
   */
  public boolean isEmpty() {
    return this.bundle.isEmpty();
  }
  
  /**
   * @return a set of all keys
   * @see android.os.Bundle#keySet()
   */
  public Set<String> keySet() {
    return this.bundle.keySet();
  }
  
  /**
   * @param map the keys and values to put in the bundle
   * @see android.os.Bundle#putAll(android.os.Bundle)
   */
  public void putAll(final Bundle map) {
    this.bundle.putAll(map);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putBoolean(java.lang.String, boolean)
   */
  public void putBoolean(final String key, final boolean value) {
    this.bundle.putBoolean(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putBooleanArray(java.lang.String, boolean[])
   */
  public void putBooleanArray(final String key, final boolean[] value) {
    this.bundle.putBooleanArray(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putBundle(java.lang.String, android.os.Bundle)
   */
  public void putBundle(final String key, final Bundle value) {
    this.bundle.putBundle(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putByte(java.lang.String, byte)
   */
  public void putByte(final String key, final byte value) {
    this.bundle.putByte(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putByteArray(java.lang.String, byte[])
   */
  public void putByteArray(final String key, final byte[] value) {
    this.bundle.putByteArray(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putChar(java.lang.String, char)
   */
  public void putChar(final String key, final char value) {
    this.bundle.putChar(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putCharArray(java.lang.String, char[])
   */
  public void putCharArray(final String key, final char[] value) {
    this.bundle.putCharArray(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putCharSequence(java.lang.String, java.lang.CharSequence)
   */
  public void putCharSequence(final String key, final CharSequence value) {
    this.bundle.putCharSequence(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putDouble(java.lang.String, double)
   */
  public void putDouble(final String key, final double value) {
    this.bundle.putDouble(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putDoubleArray(java.lang.String, double[])
   */
  public void putDoubleArray(final String key, final double[] value) {
    this.bundle.putDoubleArray(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putFloat(java.lang.String, float)
   */
  public void putFloat(final String key, final float value) {
    this.bundle.putFloat(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putFloatArray(java.lang.String, float[])
   */
  public void putFloatArray(final String key, final float[] value) {
    this.bundle.putFloatArray(key, value);
  }
  
  /**
   * Puts an int in the bundle, handling nulls
   * @param key the key at which to place the value
   * @param value the value to place at the key
   */
  public void putInt(final String key, final Integer value) {
    if (value == null) {
      return;
    }
    this.bundle.putInt(key, value.intValue());
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putIntArray(java.lang.String, int[])
   */
  public void putIntArray(final String key, final int[] value) {
    this.bundle.putIntArray(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putIntegerArrayList(java.lang.String, java.util.ArrayList)
   */
  public void putIntegerArrayList(final String key, final ArrayList<Integer> value) {
    this.bundle.putIntegerArrayList(key, value);
  }
  
  /**
   * Puts a long in the bundle, handling nulls
   * @param key the key at which to place the value
   * @param value the value to place at the key
   */
  public void putLong(final String key, final Long value) {
    if (value == null) {
      return;
    }
    this.bundle.putLong(key, value.longValue());
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putLongArray(java.lang.String, long[])
   */
  public void putLongArray(final String key, final long[] value) {
    this.bundle.putLongArray(key, value);
  }
  
  /**
   * Puts a the in the bundle, handling nulls
   * @param key the key at which to place the value
   * @param value the value to place at the key
   */
  public void putObjectToString(final String key, final Object value) {
    if (value == null) {
      return;
    }
    this.bundle.putString(key, value.toString());
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putParcelable(java.lang.String, android.os.Parcelable)
   */
  public void putParcelable(final String key, final Parcelable value) {
    this.bundle.putParcelable(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putParcelableArray(java.lang.String, android.os.Parcelable[])
   */
  public void putParcelableArray(final String key, final Parcelable[] value) {
    this.bundle.putParcelableArray(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putParcelableArrayList(java.lang.String, java.util.ArrayList)
   */
  public void putParcelableArrayList(final String key, final ArrayList<? extends Parcelable> value) {
    this.bundle.putParcelableArrayList(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putSerializable(java.lang.String, java.io.Serializable)
   */
  public void putSerializable(final String key, final Serializable value) {
    this.bundle.putSerializable(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putShort(java.lang.String, short)
   */
  public void putShort(final String key, final short value) {
    this.bundle.putShort(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putShortArray(java.lang.String, short[])
   */
  public void putShortArray(final String key, final short[] value) {
    this.bundle.putShortArray(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putSparseParcelableArray(java.lang.String, android.util.SparseArray)
   */
  public void putSparseParcelableArray(final String key,
      final SparseArray<? extends Parcelable> value) {
    this.bundle.putSparseParcelableArray(key, value);
  }
  
  /**
   * Puts a string in the bundle, handling nulls
   * @param key the key at which to place the value
   * @param value the value to place at the key
   */
  public void putString(final String key, final String value) {
    if (value == null) {
      return;
    }
    this.bundle.putString(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putStringArray(java.lang.String, java.lang.String[])
   */
  public void putStringArray(final String key, final String[] value) {
    this.bundle.putStringArray(key, value);
  }
  
  /**
   * @param key the key at which to place the value
   * @param value the value to place at the key
   * @see android.os.Bundle#putStringArrayList(java.lang.String, java.util.ArrayList)
   */
  public void putStringArrayList(final String key, final ArrayList<String> value) {
    this.bundle.putStringArrayList(key, value);
  }
  
  /**
   * @param parcel the parcel from which to read
   * @see android.os.Bundle#readFromParcel(android.os.Parcel)
   */
  public void readFromParcel(final Parcel parcel) {
    this.bundle.readFromParcel(parcel);
  }
  
  /**
   * @param key the key from which to remove the value
   * @see android.os.Bundle#remove(java.lang.String)
   */
  public void remove(final String key) {
    this.bundle.remove(key);
  }
  
  /**
   * @param loader the loader to set
   * @see android.os.Bundle#setClassLoader(java.lang.ClassLoader)
   */
  public void setClassLoader(final ClassLoader loader) {
    this.bundle.setClassLoader(loader);
  }
  
  /**
   * @return the number of items in the bundle
   * @see android.os.Bundle#size()
   */
  public int size() {
    return this.bundle.size();
  }
  
  /**
   * @return a string representation of the bundle
   * @see android.os.Bundle#toString()
   */
  @Override
  public String toString() {
    return this.bundle.toString();
  }
  
  /**
   * @param parcel the parcel to which to write
   * @param flags the flags
   * @see android.os.Bundle#writeToParcel(android.os.Parcel, int)
   */
  public void writeToParcel(final Parcel parcel, final int flags) {
    this.bundle.writeToParcel(parcel, flags);
  }
}
