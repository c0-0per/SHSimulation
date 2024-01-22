# Smart Home

Cílem našeho projektu bylo vytvořit model chytrého domu a simulaci života lidí v domě a jak budou interagovat s chytrými zařízeními.


## Patterny

- Použili jsme Builder pro generování domu

- StateMachine pro řízení zařízení

- Observer pro zpracování událostí

- Composite pro strukturu domu

- Abstract pro senzory

## Popis

Jednotlivá zařízení v domu mají API na ovládání.

Každé zařízení má svůj stav, který se může měnit.

Zařízení v domě mohou počítat množství zdrojů spotřebovaných pro jejich provoz, jako je elektřina.

Lidé mohou provádět různé aktivity v domě s zařízeními, která se mohou pravidelně porouchat.

## Diagram tříd

v souboru `class diagram.png`

## F points

F1 + v baličku "objects"

F2 + v baličku "API". Každý device ma své API a taky existuje API pro elektřinu, vodu a plyn.

F3 + device má nějaké svojí spotřebu a ve vypnutém stavu nebo zklamaném nemůže být zaapnutý - žadná spotřeba 

F4 + Každé consumption API sbírá data

F5 + Každý device má nějakou svou funkci kterou může uživatel využit (třida "Living")

F6 + Každý funkce(akce) už je určena v nějaké mistnosti

F7 + Např. WindSensor a Window

F8 + EventReport class - generate report

F9 + Prostě logger using documentation když opravuje

F10 + Simulator - startSimulation
