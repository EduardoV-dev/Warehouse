package data;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EmpresaNombre {
    private static RandomAccessFile stream;

    public static void crearArchivo(File archivo) throws IOException {
        if (archivo.exists() && !archivo.isFile()) {
            throw new IOException(archivo.getName() + " no es un archivo. ");
        }
        stream = new RandomAccessFile(archivo, "rw");
    }

    public static void establecerNombre(String nombre, boolean fijado) {
        try {
            stream.seek(0);
            stream.writeBoolean(fijado);
            stream.writeUTF(nombre);

            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String obtenerNombre() {
        try {
            stream.seek(0);
            if (stream.readBoolean()) {
                return stream.readUTF();
            }
            stream.close();
        } catch (IOException e) {
            return "";
        }
        return "";
    }

}
