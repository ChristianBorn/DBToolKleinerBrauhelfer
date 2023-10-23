export type Kapazitaet = {
    // Wird hier verwendet, da aus dem BE "Summierte Kapazität" kommt, dieser String sich aber nicht als Variablenname abbilden lässt
    // Übersetzung: Der Schlüssel einer Property muss ein string sein, der Value kann string oder number sein. Alles andere ist egal
    // So kann die Bezeichnung einer Property aus dem Backend übernommen werden -> Bei Änderungen nur Änderung im BE nötig
    [index: string]: string | number,
    name: string,
    anzahl: number,
    summierte_Kapazitaet: string
}