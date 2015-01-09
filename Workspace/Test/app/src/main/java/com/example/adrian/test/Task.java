package com.example.adrian.test;

/**
 * Created by Adrian on 2015-01-09.
 */
public class Task {
        private long id;
        private String nazwa;
         private String adres;
        private String data;

        public Task(long id, String nazwa, String adres, String data) {
            this.id = id;
            this.nazwa = nazwa;
            this.adres = adres;
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public String getAdres() {
            return adres;
        }

        public void setAdres(String adres) {
            this.adres = adres;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNazwa() {
            return nazwa;
        }

        public void setNazwa(String nazwa) {
            this.nazwa = nazwa;
        }

        public void setData(String data) {
            this.data = data;
        }
}
