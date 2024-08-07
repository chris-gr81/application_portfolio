package adWorkFrage2;

/**
 * Eine am Map-Interface orientierte Map-Implementierung auf Basis eines
 * mehrdimensionalen Arrays. Diese Implementierung bietet die wichtigsten
 * Map-Methoden <b>put, remove, get, size, clear, containsKey und
 * containsValue</b> sowie <b>toString</b> zur überischtlichen Darstellung des
 * Map-Inhalts. Die Implementierung besitzt einen Default-Konstruktor der eine
 * leere ArrayMap mit 16 Speicherplätzen für Key-Value-Paare erzeugt. Immer,
 * wenn diese Speichergrenze erreicht wird, wird die ArrayMap um weitere 16
 * Speicherplätze erweitert. Die Dublettenprüfung von übergebenen Werten und
 * Map-Inhalten erfolgt auf Basis der Rückgabewerte aus
 * <code>Object.hashCode()</code>.
 * 
 * @param <K> Datentyp für die Key-Werte
 * @param <V> Datentyp für die Value-Werte
 * 
 * @author Christian Grimm
 * @version 1.0
 */
public class ArrayMap<K, V> {
	private static final int INTERVALL = 16; // Konstante für Array-Anpassung

	private Object[][] speicher; // zentrale Datenstruktur
	private int zaehler; // Aktuelle Größe des Arrays

	/**
	 * Erzeugt eine leere ArrayMap mit einer Defaultkapazität (16)
	 */
	public ArrayMap() {
		this.speicher = new Object[INTERVALL][2];
		this.zaehler = 0;
	}

	/**
	 * Verknüpft den übergebenen Key mit dem übergebenen Value. War der Key 
	 * vorher  mit einem anderen Value verknüpft, wird der alte 
	 * Value ersetzt.
	 * 
	 * @param key   Key, mit dem der zugehörige Value verbunden ist
	 * @param value Value, mit dem der zugehörige Key verbunden ist
	 * @return Der vorherige Value, mti dem der Key verbunden war, oder
	 *         <code>null</code>, wenn der Key mit keinem Value verbunden war.
	 */
	@SuppressWarnings("unchecked")
	public V put(K key, V value) {
		// Bei Key-Dublikat: Value überschreiben und alten Wert zurück
		for (int i = 0; i < zaehler; i++) {
			if (hashVergleich(speicher[i][0], key)) {
				V temp = (V) speicher[i][1];
				speicher[i][1] = value;
				return temp;
			}
		}
		// Gegebenenfalls Array anpassen
		if (zaehler + 1 == this.speicher.length) {
			arrayDynamik(); // Array optimieren
		}
		// Einfügen der Daten
		this.speicher[zaehler][0] = key;
		this.speicher[zaehler][1] = value;
		zaehler++;

		return null;
	}

	/**
	 * Entfernt das Mapping des übergebenen Key, falls vorhanden.
	 * 
	 * @param key Key, dessen Mapping aus der ArrayMap entfernt werden soll
	 * @return Den value, der mit dem Key verbunden war, oder
	 *         <code>null</code>, falls es für den übergebenen 
	 *         Key kein Mapping gab.
	 */
	@SuppressWarnings("unchecked")
	public V remove(K key) {
		for (int i = 0; i < zaehler; i++) {
			if (hashVergleich(speicher[i][0], key)) {
				V temp = (V) speicher[i][1];

				// Zeile mit Zeile + 1 überschreiben und nachrücken
				for (int j = i; j < zaehler - 1; j++) {
					speicher[j][0] = speicher[j + 1][0];
					speicher[j][1] = speicher[j + 1][1];
				}

				// Letzte Zeile löschen (da doppelt)
				speicher[zaehler - 1][0] = null;
				speicher[zaehler - 1][1] = null;

				zaehler--;

				return temp;
			}
		}
		return null;
	}

	/**
	 * Entfernt das Mapping des übergebenen Key nur, falls vorhanden und 
	 * wenn der Key mit dem übergebenen Value verbunden ist.
	 * 
	 * @param key   Key, dessen Mapping aus der ArrayMap entfernt werden soll
	 * @param value Value des zu enfernenden Keys
	 * @return Den value, der mit dem Key verbunden war, oder
	 *         <code>null</code>,falls es für den übergebenen 
	 *         Key kein Mapping gab, oder Key und Value nicht 
	 *         zusammengepasst haben.
	 */
	public V remove(K key, V value) {
		for (int i = 0; i < zaehler; i++) {
			if (hashVergleich(speicher[i][0], key) && 
				hashVergleich(speicher[i][1], value)) {
				return this.remove(key);
			}
		}
		return null;
	}

	/**
	 * Gibt entweder den Value zurück, der mit dem übergebenen Key 
	 * gemapped ist, oder null, wenn die ArrayMap kein Mapping für 
	 * den übergebenen Key enthält.
	 * 
	 * @param key Der Key, dessen zugehöriger Value zurückgegeben werden soll.
	 * @return Den Value, der mit dem übergebenen Key gemapped ist, oder
	 *         <code>null</code>, wenn die ArrayMap kein Mapping für den 
	 *         übergebenen Key enthält.
	 */
	@SuppressWarnings("unchecked")
	public V get(K key) {
		for (int i = 0; i < zaehler; i++) {
			if (hashVergleich(speicher[i][0], key)) {
				return (V) speicher[i][1];
			}
		}
		return null;
	}

	/**
	 * Gibt die Anzahl der Key-Value-Paare in der ArrayMap zurück
	 * 
	 * @return Die Anzahl der Key-Value-Paare in der 
	 * ArrayMap als <code>int</code>
	 */
	public int size() {
		return zaehler;
	}

	/**
	 * Entfernt alle Mappings der Arraymap. Die ArrayMap ist danach 
	 * leer und auf die Default-Größe zurückgesetzt.
	 */
	public void clear() {
		Object[][] temp = new Object[INTERVALL][2];
		this.speicher = temp;
		this.zaehler = 0;
	}

	/**
	 * Gibt <code>true</code> zurück, falls der übergebene Key als 
	 * Key in der ArrayMap vorhanden ist.
	 * 
	 * @param key Der Key, dessen Vorhandensein in der ArrayMap 
	 * 		  überprüft werden soll.
	 * @return <code>true</code> falls die ArrayMap ein Mapping 
	 *         für den übergebenen Key enthält.
	 */
	public boolean containsKey(K key) {
		for (int i = 0; i < zaehler; i++) {
			if (hashVergleich(speicher[i][0], key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gibt <code>true</code> zurück, falls der übergebene Value 
	 * als Value in der ArrayMap vorhanden ist.
	 * 
	 * @param value Der Value, dessen Vorhandensein in der ArrayMap
	 *        überprüft werden soll.
	 * @return <code>true</code> falls die ArrayMap ein Mapping
	 *         für den übergebenen Value enthält.
	 */
	public boolean containsValue(V value) {
		for (int i = 0; i < zaehler; i++) {
			if (hashVergleich(speicher[i][1], value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gibt eine Repräsentanz der ArrayMap als String zurück. 
	 * Der String beinnhaltet alle gespeicherten Key-Value-Paare 
	 * nach dem Muster <code>Key | Value</code> sowie Angaben zur 
	 * aktuellen Belegung der ArrayMap und deren aktuelle 
	 * Gesamtgröße nach dem Muster <code>[Speicher: x von y]</code>. 
	 * Abgeschlossen wird der String in der letzten Zeile 
	 * durch eine gestrichelte Linie.
	 * 
	 * @return Eine String-Repräsentanz der ArrayMap
	 */
	@Override
	public String toString() {
		String ausgabe = ">>>>>> Inhalt (Key|Value):\n";
		for (int i = 0; i < this.zaehler; i++) {
			ausgabe += this.speicher[i][0] + " | " 
		             + this.speicher[i][1] + "\n";
		}
		ausgabe += "[Speicher: " + zaehler + " von " 
		         + speicher.length + "]";
		ausgabe += "\n------------------------";

		return ausgabe;
	}

	// Interne Hilfsmethoden
	private void arrayDynamik() {

		// Array mit doppelter Größe erstellen und Daten kopieren
		Object[][] temp = new Object[speicher.length + INTERVALL][2];
		for (int i = 0; i < speicher.length; i++) {
			temp[i][0] = speicher[i][0];
			temp[i][1] = speicher[i][1];
		}
		speicher = temp; // Mit speicher auf temp referenzieren
	}

	private boolean hashVergleich(Object oEins, Object oZwei) {
		// Vergleichen der Hashwerte der übergebenen Objekte
		return oEins.hashCode() == oZwei.hashCode() ? true : false;
	}
}
