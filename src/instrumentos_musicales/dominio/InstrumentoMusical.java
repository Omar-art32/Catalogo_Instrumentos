/*
 * Omar Jim�nez Hern�ndez
 * Paradigmas de programaci�n I

 */
package instrumentos_musicales.dominio;

import java.util.*;

public class InstrumentoMusical {
	// 1. N�mero libre.
	private int a�o;

	// 2. N�mero con rango.
	private double precio;

	// 3. Texto en formato libre.
	private String nombre;

	// 4. Texto con formato predefinido. 
	private String numeroSerie; //Formato es "[A-Z][0-9]{5}" T12345, 1 letra mayuscula y 5 numeros

	// 5. Fecha, que ser� un objeto de tipo Date (java.util).
	private Date fechaFabricacion;

	// 6. Dato obtenido de opciones mutuamente excluyentes fijas.
	private String condicion; // Nuevo, Usado, Reparado

	// 7. Dato obtenido de opciones mutuamente excluyentes din�micas.
	private String marca;

	// 8. Dato multivalorado de opciones no excluyentes fijas: metal, pl�stico,
	// madera, fibra, bronce, lat�n.
	private ArrayList<String> tiposMateriales;

	// 9. Dato multivalorado de opciones no excluyentes din�micas.
	private ArrayList<String> generosMusicales;

	// 10. Imagen, que ser� un String para la ruta de la imagen.
	private String rutaImagen;

	// m�todos get por defecto
    public int getA�o() {
        return a�o;
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
    public void setA�o(String a�oString) {
    	a�oString = a�oString.trim();
        int a�o = Integer.parseInt(a�oString);
        setA�o(a�o);
    }

    public void setA�o(int a�o) {
        if (a�o > 0) {
            this.a�o = a�o;
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

	// constructor sin par�metros
	public InstrumentoMusical() {
		a�o = 0;
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

	// m�todo toString
	public String toString() {
		return nombre + " " + marca + " " + numeroSerie;
	}
}