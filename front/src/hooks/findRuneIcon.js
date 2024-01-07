import { runeData } from "@/apis/runeData";

export const findMainRuneIcon = (runeId) => {
  for (const rune of runeData) {
    for (const slot of rune.slots) {
      for (const mainRune of slot.runes) {
        if (mainRune.id === runeId) {
          return mainRune.icon;
        }
      }
    }
  }
  return null;
};
export const findSubRuneIcon = (runeId) => {
  for (const rune of runeData) {
    if (rune.id === runeId) {
      return rune.icon;
    }
  }
  return null;
};
