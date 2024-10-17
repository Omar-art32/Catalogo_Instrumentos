/*
 * Omar Jiménez Hernández
 * Paradigmas de programación I

 */
package instrumentos_musicales.dominio;

import java.util.*;

public class InstrumentoMusical {
	// 1. Número libre.
	private int año;

	// 2. Número con rango.
	private double precio;

	// 3. Texto en formato libre.
	private String nombre;

	// 4. Texto con formato predefinido. 
	private String numeroSerie; //Formato es "[A-Z][0-9]{5}" T12345, 1 letra mayuscula y 5 numeros

	// 5. Fecha, que será un objeto de tipo Date (java.util).
	private Date fechaFabricacion;

	// 6. Dato obtenido de opciones mutuamente excluyentes fijas.
	private String condicion; // Nuevo, Usado, Reparado

	// 7. Dato obtenido de opciones mutuamente excluyentes dinámicas.
	private String marca;

	// 8. Dato multivalorado de opciones no excluyentes fijas: metal, plástico,
	// madera, fibra, bronce, latón.
	private ArrayList<String> tiposMateriales;

	// 9. Dato multivalorado de opciones no excluyentes dinámicas.
	private ArrayList<String> generosMusicales;

	// 10. Imagen, que será un String para la ruta de la imagen.
	private String rutaImagen;

	// métodos get por defecto
    public int getAño() {
        return año;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public String getCondicion() {
        return condicion;
    }

    public String getMarca() {
        return marca;
    }

    public ArrayList<String> getTiposMateriales() {
        return tiposMateriales;
    }

    public ArrayList<String> getGenerosMusicales() {
        return generosMusicales;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }


    // sets para variable 1
    public void setAño(String añoString) {
    	añoString = añoString.trim();
        int año = Integer.parseInt(añoString);
        setAño(año);
    }

    public void setAño(int año) {
        if (año > 0) {
            this.año = año;
        } else {
            throw new IllegalArgumentException();
        }
    }


	// sets para variable 2
	public void setPrecio(String precioString) {
		precioString = precioString.trim();
		double precio = Double.parseDouble(precioString);
		setPrecio(precio);
	}

	public void setPrecio(double precio) {
		if (precio > 0 && precio < 300000) {
			this.precio = precio;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// sets para variable 3
	public void setNombre(String nombre) {
		// Eliminar espacios en blanco de los extremos usando trim() de la Clase String
		nombre = nombre.trim();
		if (nombre.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			this.nombre = nombre;
		}
	}

	// sets para variable 4
	public void setNumeroSerie(String numeroSerie) {
		numeroSerie = numeroSerie.trim();
		if (numeroSerie.matches("[A-Z][0-9]{5}")) {
			this.numeroSerie = numeroSerie;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// sets por defecto para 5-10
	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setTiposMateriales(ArrayList<String> tiposMateriales) {
		this.tiposMateriales = tiposMateriales;
	}

	public void setGenerosMusicales(ArrayList<String> generosMusicales) {
		this.generosMusicales = generosMusicales;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	// constructor sin parámetros
	public InstrumentoMusical() {
		año = 0;
		precio = 0.0;
		nombre = "";
		numeroSerie = "";
		fechaFabricacion = null;
		condicion = "";
		marca = "";
		tiposMateriales = null;
		generosMusicales = null;
		rutaImagen = "";
	}

	// método toString
	public String toString() {
		return nombre + " " + marca + " " + numeroSerie;
	}
}