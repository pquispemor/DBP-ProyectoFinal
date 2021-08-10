/*
 * Copyright 2014 KC Ochibili
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 *  The "‚‗‚" character is not a comma, it is the SINGLE LOW-9 QUOTATION MARK unicode 201A
 *  and unicode 2017 that are used for separating the items in a list.
 */

package com.example.snackdelivery.Ayudante;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;


import com.example.snackdelivery.Dominio.SnackDominio;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class TinyDB {

    private SharedPreferences preferences;
    private String DEFAULT_APP_IMAGEDATA_DIRECTORY;
    private String lastImagePath = "";

    public TinyDB(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }


    /**
     * Decodifica el mapa de bits de 'ruta' y lo devuelve
     * @param path ruta de la imagen
     * @return el mapa de bits de 'ruta'
     */
    public Bitmap getImage(String path) {
        Bitmap bitmapFromPath = null;
        try {
            bitmapFromPath = BitmapFactory.decodeFile(path);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return bitmapFromPath;
    }


    /**
     * Devuelve la ruta de la cadena de la última imagen guardada
     * @return ruta de la cadena de la última imagen guardada
     */
    public String getSavedImagePath() {
        return lastImagePath;
    }


    /**
     * Guarda 'theBitmap' en la carpeta 'theFolder' con el nombre 'theImageName'
     * @param theFolder la ruta de la carpeta en la que desea guardarla, por ejemplo, "DropBox / WorkImages"
     * @param theImageName el nombre que desea asignar al archivo de imagen, por ejemplo, "MeAtLunch.png"
     * @param theBitmap la imagen que desea guardar como un mapa de bits
     * @return devuelve la ruta completa (dirección del sistema de archivos) de la imagen guardada
     */
    public String putImage(String theFolder, String theImageName, Bitmap theBitmap) {
        if (theFolder == null || theImageName == null || theBitmap == null)
            return null;

        this.DEFAULT_APP_IMAGEDATA_DIRECTORY = theFolder;
        String mFullPath = setupFullPath(theImageName);

        if (!mFullPath.equals("")) {
            lastImagePath = mFullPath;
            saveBitmap(mFullPath, theBitmap);
        }

        return mFullPath;
    }


    /**
     *Guarda 'theBitmap' en 'fullPath'
     * @param fullPath ruta completa del archivo de imagen, p. ej. "Images / MeAtLunch.png"
     * @param theBitmap la imagen que desea guardar como un mapa de bits
     * @return verdadero si la imagen fue guardada, falso en caso contrario
     */
    public boolean putImageWithFullPath(String fullPath, Bitmap theBitmap) {
        return !(fullPath == null || theBitmap == null) && saveBitmap(fullPath, theBitmap);
    }

    /**
     * Crea la ruta para la imagen con el nombre 'imageName' en el directorio DEFAULT_APP ..
     * @param imageName nombre de la imagen
     * @return la ruta completa de la imagen. Si no pudo crear el directorio, devuelva una cadena vacía
     */
    private String setupFullPath(String imageName) {
        File mFolder = new File(Environment.getExternalStorageDirectory(), DEFAULT_APP_IMAGEDATA_DIRECTORY);

        if (isExternalStorageReadable() && isExternalStorageWritable() && !mFolder.exists()) {
            if (!mFolder.mkdirs()) {
                Log.e("ERROR", "Failed to setup folder");
                return "";
            }
        }

        return mFolder.getPath() + '/' + imageName;
    }

    /**
     * Guarda el mapa de bits como un archivo PNG en la ruta 'fullPath'
     * @param fullPath ruta del archivo de imagen
     * @param bitmap de bits la imagen como un mapa de bits
     * @return verdadero si se guardó correctamente, falso en caso contrario
     */
    private boolean saveBitmap(String fullPath, Bitmap bitmap) {
        if (fullPath == null || bitmap == null)
            return false;

        boolean fileCreated = false;
        boolean bitmapCompressed = false;
        boolean streamClosed = false;

        File imageFile = new File(fullPath);

        if (imageFile.exists())
            if (!imageFile.delete())
                return false;

        try {
            fileCreated = imageFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(imageFile);
            bitmapCompressed = bitmap.compress(CompressFormat.PNG, 100, out);

        } catch (Exception e) {
            e.printStackTrace();
            bitmapCompressed = false;

        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                    streamClosed = true;

                } catch (IOException e) {
                    e.printStackTrace();
                    streamClosed = false;
                }
            }
        }

        return (fileCreated && bitmapCompressed && streamClosed);
    }

    // Getters

    /**
     * Obtenga el valor int de SharedPreferences en 'key'. Si no se encuentra la clave, devuelva 0
     * @param key Clave SharedPreferences
     * @return int value en 'key' o 0 si no se encuentra la clave
     */
    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    /**
     * Obtenga ArrayList of Integers analizada de SharedPreferences en 'key'
     * @param key Clave SharedPreferences
     *@return ArrayList of Integers
     */
    public ArrayList<Integer> getListInt(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
        ArrayList<Integer> newList = new ArrayList<Integer>();

        for (String item : arrayToList)
            newList.add(Integer.parseInt(item));

        return newList;
    }

    /**
     * Obtenga un valor largo de SharedPreferences en 'clave'. Si no se encuentra la clave, devuelva 0
     * @param key Clave SharedPreferences
     * @return valor largo en 'clave' o 0 si no se encuentra la clave
     */
    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }

    /**
     * Obtenga el valor flotante de SharedPreferences en 'clave'. Si no se encuentra la clave, devuelva 0
     * @param key Clave SharedPreferences
     * @return valor flotante en 'clave' o 0 si no se encuentra la clave
     */
    public float getFloat(String key) {
        return preferences.getFloat(key, 0);
    }

    /**
     * Obtenga el doble de valor de SharedPreferences en 'clave'. Si se lanza una excepción, devuelve 0
     * @param key Clave SharedPreferences
     * @return valor doble en 'clave' o 0 si se lanza una excepción
     */
    public double getDouble(String key) {
        String number = getString(key);

        try {
            return Double.parseDouble(number);

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Obtenga ArrayList analizado de Double de SharedPreferences en 'clave'
     * @param key Clave SharedPreferences
     * @return ArrayList de Double
     */
    public ArrayList<Double> getListDouble(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
        ArrayList<Double> newList = new ArrayList<Double>();

        for (String item : arrayToList)
            newList.add(Double.parseDouble(item));

        return newList;
    }

    /**
     * Obtenga ArrayList of Integers analizada de SharedPreferences en 'key'
     * @param key Clave SharedPreferences
     * @return ArrayList of Longs
     */
    public ArrayList<Long> getListLong(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
        ArrayList<Long> newList = new ArrayList<Long>();

        for (String item : arrayToList)
            newList.add(Long.parseLong(item));

        return newList;
    }

    /**
     * Obtener valor de cadena de SharedPreferences en 'clave'. Si no se encuentra la clave, devuelva ""
     * @param key Clave SharedPreferences
     * @return Valor de cadena en 'clave' o "" (Cadena vacía) si no se encuentra la clave
     */
    public String getString(String key) {
        return preferences.getString(key, "");
    }

    /**
     * Obtenga ArrayList analizado de String de SharedPreferences en 'clave'
     * @param key Clave SharedPreferences
     * @return ArrayList of String
     */
    public ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
    }

    /**
     * Obtenga el valor booleano de SharedPreferences en 'clave'. Si no encuentra la clave, devuelva falso
     * @param key Clave SharedPreferences
     * @return valor booleano en 'clave' o falso si no se encuentra la clave
     */
    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    /**
     * Obtenga ArrayList analizado de Boolean de SharedPreferences en 'clave'
     * @param key Clave SharedPreferences
     * @return ArrayList de Boolean
     */
    public ArrayList<Boolean> getListBoolean(String key) {
        ArrayList<String> myList = getListString(key);
        ArrayList<Boolean> newList = new ArrayList<Boolean>();

        for (String item : myList) {
            if (item.equals("true")) {
                newList.add(true);
            } else {
                newList.add(false);
            }
        }

        return newList;
    }


    public ArrayList<SnackDominio> getListObject(String key){
        Gson gson = new Gson();

        ArrayList<String> objStrings = getListString(key);
        ArrayList<SnackDominio> playerList =  new ArrayList<SnackDominio>();

        for(String jObjString : objStrings){
            SnackDominio player  = gson.fromJson(jObjString,  SnackDominio.class);
            playerList.add(player);
        }
        return playerList;
    }



    public <T> T getObject(String key, Class<T> classOfT){

        String json = getString(key);
        Object value = new Gson().fromJson(json, classOfT);
        if (value == null)
            throw new NullPointerException();
        return (T)value;
    }


    // Poner métodos

    /**
     * Ponga el valor int en SharedPreferences con 'key' y guarde
     * @param key Clave SharedPreferences
     * @param value int valor que se agregará
     */
    public void putInt(String key, int value) {
        checkForNullKey(key);
        preferences.edit().putInt(key, value).apply();
    }

    /**
     * Coloque ArrayList of Integer en SharedPreferences con 'key' y guarde
     * @param key Clave SharedPreferences
     * @param intList ArrayList de Integer que se agregará
     */
    public void putListInt(String key, ArrayList<Integer> intList) {
        checkForNullKey(key);
        Integer[] myIntList = intList.toArray(new Integer[intList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myIntList)).apply();
    }

    /**
     * Ponga un valor largo en SharedPreferences con 'key' y guarde
     * @param key Clave SharedPreferences
     * @param value valor largo que se agregará
     */
    public void putLong(String key, long value) {
        checkForNullKey(key);
        preferences.edit().putLong(key, value).apply();
    }

    /**
     * Coloque ArrayList of Long en SharedPreferences con 'key' y guarde
     * @param key Clave SharedPreferences
     * @param longList ArrayList of Long que se agregará
     */
    public void putListLong(String key, ArrayList<Long> longList) {
        checkForNullKey(key);
        Long[] myLongList = longList.toArray(new Long[longList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myLongList)).apply();
    }

    /**
     * Ponga el valor flotante en SharedPreferences con 'key' y guarde
     * @param key Clave SharedPreferences
     * Valor de @param valor flotante que se agregará
     */
    public void putFloat(String key, float value) {
        checkForNullKey(key);
        preferences.edit().putFloat(key, value).apply();
    }

    /**
     * Ponga doble valor en SharedPreferences con 'key' y guarde
     * @param key Clave SharedPreferences
     * valor @param valor doble que se agregará
     */
    public void putDouble(String key, double value) {
        checkForNullKey(key);
        putString(key, String.valueOf(value));
    }

    /**
     * Coloque ArrayList of Double en SharedPreferences con 'key' y guarde
     * @param key Clave SharedPreferences
     * @param doubleList ArrayList of Double que se agregará
     */
    public void putListDouble(String key, ArrayList<Double> doubleList) {
        checkForNullKey(key);
        Double[] myDoubleList = doubleList.toArray(new Double[doubleList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myDoubleList)).apply();
    }

    /**
     * Ponga el valor de cadena en SharedPreferences con 'clave' y guarde
     * @param key Clave SharedPreferences
     * @param value Valor de cadena que se agregará
     */
    public void putString(String key, String value) {
        checkForNullKey(key); checkForNullValue(value);
        preferences.edit().putString(key, value).apply();
    }

    /**
     * Coloque ArrayList of String en SharedPreferences con 'key' y guarde
     * @param key Clave SharedPreferences
     * @param stringList ArrayList of String que se agregará
     */
    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }

    /**
     * Ponga un valor booleano en SharedPreferences con 'key' y guarde
     * @param key Clave SharedPreferences
     * @param value valor booleano que se agregará
     */
    public void putBoolean(String key, boolean value) {
        checkForNullKey(key);
        preferences.edit().putBoolean(key, value).apply();
    }

    /**
     * Coloque ArrayList of Boolean en SharedPreferences con 'key' y guarde
     * @param key Clave SharedPreferences
     * @param boolList ArrayList de booleano que se agregará
     */
    public void putListBoolean(String key, ArrayList<Boolean> boolList) {
        checkForNullKey(key);
        ArrayList<String> newList = new ArrayList<String>();

        for (Boolean item : boolList) {
            if (item) {
                newList.add("true");
            } else {
                newList.add("false");
            }
        }

        putListString(key, newList);
    }

    /**
     * Ponga ObJect de cualquier tipo en SharedPrefrences con 'key' y guarde
     * @param key Clave SharedPreferences
     * @param obj es el objeto que desea poner
     */
    public void putObject(String key, Object obj){
    	checkForNullKey(key);
    	Gson gson = new Gson();
    	putString(key, gson.toJson(obj));
    }

    public void putListObject(String key, ArrayList<SnackDominio> playerList){
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for(SnackDominio player: playerList){
            objStrings.add(gson.toJson(player));
        }
        putListString(key, objStrings);
    }

    /**
     * Eliminar elemento SharedPreferences con 'clave'
     * @param key Clave SharedPreferences
     */
    public void remove(String key) {
        preferences.edit().remove(key).apply();
    }

    /**
     * Eliminar archivo de imagen en 'ruta'
     * @param path ruta del archivo de imagen
     * @return verdadero si se eliminó correctamente, falso en caso contrario
     */
    public boolean deleteImage(String path) {
        return new File(path).delete();
    }


    /**
     * Borrar SharedPreferences (eliminar todo)
     */
    public void clear() {
        preferences.edit().clear().apply();
    }

    /**
     * Recupere todos los valores de SharedPreferences. No modifique la devolución de la colección por método
     * @return un mapa que representa una lista de pares clave / valor de SharedPreferences
     */
    public Map<String, ?> getAll() {
        return preferences.getAll();
    }


    /**
     * Registrar el oyente de cambios de SharedPreferences
     * @param listener objeto de escucha de OnSharedPreferenceChangeListener
     */
    public void registerOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {

        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * Anular el registro de SharedPreferences change oyente
     * El objeto de escucha de @param listener de OnSharedPreferenceChangeListener se anulará del registro
     */
    public void unregisterOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {

        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }


    /**
     * Compruebe si se puede escribir en el almacenamiento externo o no
     * @return verdadero si se puede escribir, falso en caso contrario
     */
    public static boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * Compruebe si el almacenamiento externo es legible o no
     * @return verdadero si se puede leer, falso en caso contrario
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
    /**
     * Las claves nulas corromperían el archivo de preferencias compartido y lo harían ilegible. Esta es una medida preventiva.
     * @param key la clave pref para verificar
     */
    private void checkForNullKey(String key){
        if (key == null){
            throw new NullPointerException();
        }
    }
    /**
     * Las claves nulas corromperían el archivo de preferencias compartido y lo harían ilegible. Esta es una medida preventiva.
     * @param value el valor pref para verificar
     */
    private void checkForNullValue(String value){
        if (value == null){
            throw new NullPointerException();
        }
    }
}