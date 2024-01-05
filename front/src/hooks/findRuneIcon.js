import { runeData } from "@/apis/runeData";

export const findRuneIcon = (runeId) => {
  for (const rune of runeData) {
    for (const slot of rune.slots) {
      for (const subRune of slot.runes) {
        if (subRune.id === runeId) {
          return subRune.icon;
        }
      }
    }
  }
  return null;
};
