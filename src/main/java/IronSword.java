public class IronSword extends Weapon {
    public IronSword(int phase) {
        kode = 1;
        this.phase = phase;
        bonus = 0;
        damage = 10;
        lvl = 0;
    }

    //buat ngereturn damagenya seusai perhitungan
    public int getDamage(){
        return damage*(2*phase)+(3+bonus)+(4+lvl);
    }

    //buat kalau upgrade senjata
    public void tambahLvl(){
        lvl++;
    }

    //kalau sudah ganti phase yang terjadi setiap 9 wave
    //arcade mode gak bisa ganti phase
    public void gantiPhase(){
        phase++;
        bonus += lvl;
        lvl = 0;
    }
}
