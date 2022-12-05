package com.example.disfrazitos.Entidades;

public class Pedido {
    String pid;
    int id_pedido;
    String cliente_pedido;
    String telefono_pedido;
    String direccion_pedido;
    String referencia_pedido;
    String puntoEntrega_pedido;
    double latitud_pedido;
    double longitud_pedido;
    String estado_pedido;
    String photo_pedido;
    String fecha_pedido;
    String imagen_disfraz;
    String nombre_disfraz;
    String descripcion_disfraz;
    String talla_disfraz;
    int stock_disfraz;
    float precio_disfraz;
    int cantidadComprar_disfraz;
    float precioTotal_disfraz;


    public Pedido() {
    }

    public Pedido(String pid, int id_pedido, String cliente_pedido, String telefono_pedido, String direccion_pedido, String referencia_pedido, String puntoEntrega_pedido, double latitud_pedido, double longitud_pedido, String estado_pedido, String photo_pedido, String fecha_pedido, String imagen_disfraz, String nombre_disfraz, String descripcion_disfraz, String talla_disfraz, int stock_disfraz, float precio_disfraz, int cantidadComprar_disfraz, float precioTotal_disfraz) {
        this.pid = pid;
        this.id_pedido = id_pedido;
        this.cliente_pedido = cliente_pedido;
        this.telefono_pedido = telefono_pedido;
        this.direccion_pedido = direccion_pedido;
        this.referencia_pedido = referencia_pedido;
        this.puntoEntrega_pedido = puntoEntrega_pedido;
        this.latitud_pedido = latitud_pedido;
        this.longitud_pedido = longitud_pedido;
        this.estado_pedido = estado_pedido;
        this.photo_pedido = photo_pedido;
        this.fecha_pedido = fecha_pedido;
        this.imagen_disfraz = imagen_disfraz;
        this.nombre_disfraz = nombre_disfraz;
        this.descripcion_disfraz = descripcion_disfraz;
        this.talla_disfraz = talla_disfraz;
        this.stock_disfraz = stock_disfraz;
        this.precio_disfraz = precio_disfraz;
        this.cantidadComprar_disfraz = cantidadComprar_disfraz;
        this.precioTotal_disfraz = precioTotal_disfraz;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getCliente_pedido() {
        return cliente_pedido;
    }

    public void setCliente_pedido(String cliente_pedido) {
        this.cliente_pedido = cliente_pedido;
    }

    public String getTelefono_pedido() {
        return telefono_pedido;
    }

    public void setTelefono_pedido(String telefono_pedido) {
        this.telefono_pedido = telefono_pedido;
    }

    public String getDireccion_pedido() {
        return direccion_pedido;
    }

    public void setDireccion_pedido(String direccion_pedido) {
        this.direccion_pedido = direccion_pedido;
    }

    public String getReferencia_pedido() {
        return referencia_pedido;
    }

    public void setReferencia_pedido(String referencia_pedido) {
        this.referencia_pedido = referencia_pedido;
    }

    public String getPuntoEntrega_pedido() {
        return puntoEntrega_pedido;
    }

    public void setPuntoEntrega_pedido(String puntoEntrega_pedido) {
        this.puntoEntrega_pedido = puntoEntrega_pedido;
    }

    public double getLatitud_pedido() {
        return latitud_pedido;
    }

    public void setLatitud_pedido(double latitud_pedido) {
        this.latitud_pedido = latitud_pedido;
    }

    public double getLongitud_pedido() {
        return longitud_pedido;
    }

    public void setLongitud_pedido(double longitud_pedido) {
        this.longitud_pedido = longitud_pedido;
    }

    public String getEstado_pedido() {
        return estado_pedido;
    }

    public void setEstado_pedido(String estado_pedido) {
        this.estado_pedido = estado_pedido;
    }

    public String getPhoto_pedido() {
        return photo_pedido;
    }

    public void setPhoto_pedido(String photo_pedido) {
        this.photo_pedido = photo_pedido;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public String getImagen_disfraz() {
        return imagen_disfraz;
    }

    public void setImagen_disfraz(String imagen_disfraz) {
        this.imagen_disfraz = imagen_disfraz;
    }

    public String getNombre_disfraz() {
        return nombre_disfraz;
    }

    public void setNombre_disfraz(String nombre_disfraz) {
        this.nombre_disfraz = nombre_disfraz;
    }

    public String getDescripcion_disfraz() {
        return descripcion_disfraz;
    }

    public void setDescripcion_disfraz(String descripcion_disfraz) {
        this.descripcion_disfraz = descripcion_disfraz;
    }

    public String getTalla_disfraz() {
        return talla_disfraz;
    }

    public void setTalla_disfraz(String talla_disfraz) {
        this.talla_disfraz = talla_disfraz;
    }

    public int getStock_disfraz() {
        return stock_disfraz;
    }

    public void setStock_disfraz(int stock_disfraz) {
        this.stock_disfraz = stock_disfraz;
    }

    public float getPrecio_disfraz() {
        return precio_disfraz;
    }

    public void setPrecio_disfraz(float precio_disfraz) {
        this.precio_disfraz = precio_disfraz;
    }

    public int getCantidadComprar_disfraz() {
        return cantidadComprar_disfraz;
    }

    public void setCantidadComprar_disfraz(int cantidadComprar_disfraz) {
        this.cantidadComprar_disfraz = cantidadComprar_disfraz;
    }

    public float getPrecioTotal_disfraz() {
        return precioTotal_disfraz;
    }

    public void setPrecioTotal_disfraz(float precioTotal_disfraz) {
        this.precioTotal_disfraz = precioTotal_disfraz;
    }
}
