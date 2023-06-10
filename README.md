# Projek PBO

* Dependency di resolve menggunakan maven, jadi tidak usah download librarynya lagi,
* Saat ini library processing yang digunakan adalah processing-4 dan juga JUnit 5
* Sebisa mungkin per orang bikin branchnya masing masing biar kalau ada conflict bisa diresolve ya, belakangan baru di merge.


# Anggota Kelompok

* Dahren Ericsson - 222117013
* Ferdinand Susanto Halim - 222117021
* Jasson Putra Sanjaya - 222117031
* Jonathan Kenrick - 222117036

# Escape The Dungeon


## Task Description

Dalam tugas ini, Kita akan membuat game dengan menggunakan java menggunakan processing library untuk graphics. Game yang diharuskan itu bebas, sesuai dengan kemampuan kita. Oleh karena itu, kita memutuskan untuk membuat game yang terinspirasi oleh game yang mendunia, The Binding of Isaac. Gamenya bersifat rouge-like, kita bermain sebagai seorang survivor yang tujuannya adalah keluar dari tempat tersebut, di setiap tempat akan memiliki musuh yang berpatroli di sekitar tempat tersebut. Kita sebagai survivor dapat membunuh musuh tersebut dengan senjata yang dimiliki, senjata tersebut bisa di upgrade supaya dapat menangani musuh di level akhir. Game ini memiliki berbagai level, dari level dasar sampai level paling atas. Di level paling atas akan terdapat big boss yang merupakan rintangan terakhir dari sebuah game tersebut, tujuan kita adalah mengalahkan boss tersebut.


## Gameplay

Game ini memiliki 2 mode yaitu arcade mode dan endless mode

Arcade mode

entities.Player adalah seseorang yang terjebak didalam sebuah tower yang memiliki 5 lantai. Tujuan dari player adalah bebas dari tower tersebut dengan menyelesaikan masing masing floor dengan membunuh semua enemy yang ada di lantai tersebut. Di setiap lantai akan memiliki 3 wave dimana wave pertama akan langsung dimulai saat player memasuki floor dan pintu selanjutnya akan terbuka setelah player menyelesaikan ketiga tiga wave tersebut.

Jika player mati maka semua status yang telah didapat oleh player akan hilang kembali ke default dan mengulang dari floor 1.

Endless mode

Yaitu dimana game tersebut akan tidak akan berhenti sampai player mati (endless wave). Setiap wave akan menjadi lebih sulit dan setiap 3 wave akan ada upgrade antara heal atau upgrade weapon, atau change weapon.

Setiap 9 wave musuhnya juga akan semakin sulit dan kuat.

## MAP

Besar mapnya adalah tiles 64x32. Setiap tile memiliki besar 20x20 pixel. Sehingga total resolusi adalah 1280x640 pixel. entities.Player akan summon dari gerbang awal. 80 pixel diatas map akan digunakan untuk memberikan informasi jumlah darah player sekarang, jumlah musuh tersisa, lantai tower, informasi power up dan sisa waktunya. Jadi total resolusi game adalah 1280x720.

Ada 3 tipe tiles disini:

Floor
Wall
Hole (khusus arcade mode)

Setiap tiles akan berbeda tergantung tiap level dimana player berada.

Setelah setiap floor diselesaikan akan terbuka chest di akhir floor yang berisi hadiah power up secara random seperti nambah health atau ganti senjata atau upgrade senjata.

## entities.Player

entities.Player dikontrol dengan menggunakan WASD. entities.Player memiliki 3 heart di awal floor. entities.Player hanya bisa bergerak atas, bawah, kiri, kanan. pergerakannya harus bergerak dengan halus. entities.Player akan mulai di gerbang awal floor. Pergerakan akan terus berjalan selama key ditekan dan akan berhenti jika keynya dilepas. Kecepatan dan arah akan selalu sama sampai key lain ditekan. entities.Player akan terus menyerang dengan selang waktu setengah detik ke arah dimana player menghadap.

Jika player mati maka player akan mengulang dari floor 1.

* entities.Player bisa menggunakan senjata vampire sword bisa life steal 2% kemungkinan.
* entities.Player bisa menggunakan senjata iron sword yaitu senjata awal.
* entities.Player bisa menggunakan senjata bow yang memiliki jarak jauh.

entities.Player hanya bisa memiliki 1 senjata.

setiap kali pergantian senjata, senjata yang lama akan hilang dan jika mengambil lagi senjata yang sama seperti yang sudah dibuang maka levelnya akan kembali seperti awal.
Tapi pada mode endless, setiap 9 wave akan ada penyesuaian damage agar pada saat mengganti senjata damagenya tidak terlalu lemah dan akan diberikan bonus untuk senjata yang sedang dipegang sekarang tergantung berapa levelnya.


## Enemies

Ada 2 musuh utama disini: zombie dan skeleton. Dimana zombie dan skeleton merupakan tipe enemy normal, artinya akan muncul di setiap level, namun tiap beberapa level, contohnya lantai 3 akan  muncul enemy tipe elite yang memiliki serangan khusus dan buff.

Elite enemies umumnya memiliki darah yang lebih tebal daripada enemy normal dan tidak akan selalu muncul.

Di level terakhir game ini, akan muncul big boss dimana akan memiliki darah yang lebih tebal dibandingkan elite enemies dan menyerang dengan damage AOE.

## POWERUP

Dalam setiap level akan terdapat powerup yang bisa spawn di tempat tertentu, powerup ini akan bersifat hanya bertahan selama beberapa waktu saja, contohnya 30 detik, 1 menit.

Powerup yang bisa diperoleh misalnya :
Speed boost ( 10 detik )
Shield ( sampai shield nya hancur  atau ke level selanjutnya )
Attack speed boost ( 15 detik )
Freeze all ( 5 detik )
