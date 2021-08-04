package com.nodoclic.cic.clases;

    public class RsMenu {
        private int id;
        private int tipo;
        private String nombre;
        private String fecha;
        private int estado;
        private int cantidadinicial;
        private int cantidadactual;
        private int comida;

        public RsMenu(int id, int tipo, String nombre, String fecha, int estado, int cantidadinicial, int cantidadactual, int comida) {
            this.id = id;
            this.tipo = tipo;
            this.nombre = nombre;
            this.fecha = fecha;
            this.estado = estado;
            this.cantidadinicial = cantidadinicial;
            this.cantidadactual = cantidadactual;
            this.comida = comida;
        }


        // Getter Methods

        public int getId() {
            return id;
        }

        public int getTipo() {
            return tipo;
        }

        public String getNombre() {
            return nombre;
        }

        public String getFecha() {
            return fecha;
        }

        public int getEstado() {
            return estado;
        }

        public int getCantidadinicial() {
            return cantidadinicial;
        }

        public int getCantidadactual() {
            return cantidadactual;
        }

        public int getComida() {
            return comida;
        }

        // Setter Methods

        public void setId(int id) {
            this.id = id;
        }

        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }

        public void setCantidadinicial(int cantidadinicial) {
            this.cantidadinicial = cantidadinicial;
        }

        public void setCantidadactual(int cantidadactual) {
            this.cantidadactual = cantidadactual;
        }

        public void setComida(int comida) {
            this.comida = comida;
        }
    }