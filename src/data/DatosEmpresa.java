package data;

import models.DB.Empresa;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DatosEmpresa {
    private static RandomAccessFile stream;

    public static void crearArchivo(File archivo) throws IOException {
        if (archivo.exists() && !archivo.isFile()) {
            throw new IOException(archivo.getName() + " no es un archivo. ");
        }
        stream = new RandomAccessFile(archivo, "rw");
    }

    public static void establecerEmpresa(Empresa empresa) {
        try {
            stream.seek(0);
            stream.writeUTF(empresa.getNombre());
            stream.writeUTF(empresa.getCorreo());
            stream.writeUTF(empresa.getTelefono());
            stream.writeUTF(empresa.getRIF());
            stream.writeUTF(empresa.getDireccion());

            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Empresa obtenerDatosEmpresa() {
        try {
            stream.seek(0);
            if (stream.readUTF().isEmpty() || stream.readUTF() == null) {
                return new Empresa("", "", "", "", "");
            } else {
                stream.seek(0);
                return new Empresa(
                        stream.readUTF(),
                        stream.readUTF(),
                        stream.readUTF(),
                        stream.readUTF(),
                        stream.readUTF()
                );
            }
        } catch (IOException e) {
            return new Empresa("", "", "", "", "");
        }
    }

}
