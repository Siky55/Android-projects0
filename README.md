# Aplikace ToneMate

## Autor
Jan Szikora

## Popis

ToneMate je aplikací pro hudebníky, s cílem pomoci jim rozvíjet se v oboru hudby. Poskytuje uživateli celkem 4 nástroje/programy - Circle, Quiz, Xylophone, Metronome.
Program **Circle** je interaktivní vizualizací kruhu kvint.
Program **Quiz** je klasickým testem pro ověření znalostí hudebních stupnic.
Program **Xylophone** je jednoduchou simulací xylofonu.
Program **Metronome** je jednoduchou simulací metronomu.

## Obsah

- [Instalace](#instalace)
- [Požadavky](#požadavky)
  - [Softwarové požadavky](#softwarové-požadavky)
  - [Hardwarové požadavky (pro Android Studio)](#hardwarové-požadavky-pro-android-studio)
  - [Další nástroje](#další-nástroje)
- [Architektura](#architektura)
- [Programovací jazyky](#programovací-jazyky)

## Instalace

Instrukce pro instalaci projektu:

1. Klonování repozitáře:
    ```bash
    git clone https://github.com/Siky55/Android-projects0
    ```

2. Otevření projektu v Android Studio:
    - Spusťte Android Studio.
    - Klikněte na `Open an existing Android Studio project`.
    - Vyberte adresář s projektem.

3. Synchronizace závislostí:
    - Android Studio by mělo automaticky synchronizovat projekt. Pokud ne, klikněte na `File > Sync Project with Gradle Files`.

4. Spuštění aplikace:
    - Připojte fyzické zařízení nebo spusťte emulátor.
    - Klikněte na `Run > Run 'app'`.

## Požadavky

Před začátkem práce s projektem se ujistěte, že máte nainstalovány následující nástroje a splňujete požadavky:

### Softwarové požadavky

- **Operační systém**: Windows, macOS, nebo Linux
- **Android Studio**: Nejnovější verze [Android Studio](https://developer.android.com/studio)
- **Java Development Kit (JDK)**: Verze 11 nebo vyšší [JDK ke stažení](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Android SDK**: Součástí Android Studia
- **Gradle**: Není nutné instalovat samostatně, Android Studio používá vestavěný Gradle Wrapper

### Hardwarové požadavky (pro Android Studio)

- **RAM**: Minimálně 8 GB (doporučeno 16 GB pro lepší výkon)
- **Disk**: Minimálně 4 GB volného místa (doporučeno SSD pro lepší výkon)
- **Procesor**: 64-bitový procesor

### Další nástroje

- **Git**: Pro správu verzí [Git ke stažení](https://git-scm.com/)
- **Emulátor nebo fyzické zařízení**: Pro testování aplikace

## Architektura

MVVM (Model-View-ViewModel)

## Programovací jazyky
- JAVA