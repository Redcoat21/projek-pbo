public abstract class Weapon {
    protected int kode;
    //kode 1 = ironsword
    //kode 2 = panah
    //kode 3 = vampire sword
    //probabilitas kemunculan
    //kalau sekarang kode 1, kode 2 90% kode 3 10%
    //kalau sekarang kode 2, kode 1 90% kode 3 10%
    //kalau sekarang kode 3, kode 1 50% kode 2 50%
    protected int damage;
    protected int phase;
    //phase digunakan buat ngasih bates minimum jadi tahu sekarang itu phase berapa. perhitungannya adalah wave/3
    protected int lvl;
    //kalau pilih upgrade maka lvlnya akan bertambah
    //setiap phase bertambah levelnya pasti akan jadi 0 lagi untuk semua senjata
    protected int bonus;
    //bonus buat nyimpen berapa lvl sebelum naik phase kalau ganti senjata jadi 0 lagi
}
