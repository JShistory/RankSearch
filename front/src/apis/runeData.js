export const runeData = [
  {
    id: 8100,
    key: "Domination",
    icon: "perk-images/Styles/7200_Domination.png",
    name: "지배",
    slots: [
      {
        runes: [
          {
            id: 8112,
            key: "Electrocute",
            icon: "perk-images/Styles/Domination/Electrocute/Electrocute.png",
            name: "감전",
            shortDesc:
              "3초 동안 같은 챔피언에게 기본 공격 또는 <b>개별</b> 스킬 3회를 적중시키면 추가 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'>적응형 피해</lol-uikit-tooltipped-keyword> 적용",
            longDesc:
              "3초 동안 같은 챔피언에게 <b>개별</b> 공격 또는 스킬을 3회 적중시키면 추가 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'><font color='#48C4B7'>적응형 피해</font></lol-uikit-tooltipped-keyword>를 입힙니다.<br><br>피해량: 30~180 (+추가 공격력의 0.4, +주문력의 0.25)<br><br>재사용 대기시간: 25~20초<br><br><hr><i>'우리는 그들을 천둥군주라고 부른다. 그들의 번개를 입에 올리는 것은 재앙을 부르는 길이기 때문이다.'</i>",
          },
          {
            id: 8124,
            key: "Predator",
            icon: "perk-images/Styles/Domination/Predator/Predator.png",
            name: "포식자",
            shortDesc:
              "장화에 사용 효과 추가. 사용 시 일시적으로 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>이동 속도</lol-uikit-tooltipped-keyword>가 대폭 증가하며 다음 공격 또는 스킬 사용 시 추가 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'>적응형 피해</lol-uikit-tooltipped-keyword>",
            longDesc:
              "장화에 '<font color='#c60300'>포식자</font>' 사용 효과를 부여합니다.<br><br>적 챔피언을 쫓을 때 이동 속도가 1초 동안 25~50%까지 서서히 증가합니다. 이후 챔피언에게 공격 또는 스킬 사용 시 이 효과가 종료되며 20~180(+추가 공격력의 <scaleAD>0.25</scaleAD>)(+주문력의 <scaleAP>0.15</scaleAP>)의 추가 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'><font color='#48C4B7'>적응형 피해</font></lol-uikit-tooltipped-keyword>를 입힙니다.<br><br>재사용 대기시간: 120~60초",
          },
          {
            id: 8128,
            key: "DarkHarvest",
            icon: "perk-images/Styles/Domination/DarkHarvest/DarkHarvest.png",
            name: "어둠의 수확",
            shortDesc:
              "체력이 낮은 챔피언에게 피해를 입히면 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'>적응형 피해</lol-uikit-tooltipped-keyword>를 입히고 해당 챔피언의 영혼을 수확",
            longDesc:
              "체력이 50%보다 낮은 챔피언에게 피해를 입히면 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'>적응형 피해</lol-uikit-tooltipped-keyword>를 추가로 입히고 해당 챔피언의 영혼을 수확해 어둠의 수확 효과의 피해량이 영구적으로 5만큼 증가합니다.<br><br>어둠의 수확 피해량: 20~60 (레벨에 비례) (+영혼당 피해량 5) (+추가 공격력의 0.25) (+주문력의 0.15)<br>재사용 대기시간: 45초 (처치 관여 시 1.5초로 초기화)",
          },
          {
            id: 9923,
            key: "HailOfBlades",
            icon: "perk-images/Styles/Domination/HailOfBlades/HailOfBlades.png",
            name: "칼날비",
            shortDesc: "적 챔피언에 대한 첫 공격 3회 동안 공격 속도 대폭 증가",
            longDesc:
              "적 챔피언에 대한 3번째 공격까지 공격 속도가 110% 증가합니다.<br><br>3초 안에 다음 공격을 가하지 못하면 효과가 사라집니다.<br><br>재사용 대기시간: 12초<br><br><rules>기본 공격 모션이 취소될 경우 공격 속도 증가 효과가 적용되는 공격 횟수가 1회 늘어납니다.<br>일시적으로 최고 공격 속도 제한을 초과할 수 있습니다.</rules>",
          },
        ],
      },
      {
        runes: [
          {
            id: 8126,
            key: "CheapShot",
            icon: "perk-images/Styles/Domination/CheapShot/CheapShot.png",
            name: "비열한 한 방",
            shortDesc:
              "<lol-uikit-tooltipped-keyword key='LinkTooltip_Description_ImpairAct'>이동 또는 행동이 제약된</lol-uikit-tooltipped-keyword> 적 챔피언에게 추가 고정 피해 ",
            longDesc:
              "<b>이동 또는 행동을 방해받은</b> 상태의 챔피언에게 피해를 주면 레벨에 따라 10 ~ 45의 추가 고정 피해를 입힙니다.<br><br>재사용 대기시간: 4초<br><rules>방해 효과 이후 피해를 가할 때 활성화됩니다.</rules>",
          },
          {
            id: 8139,
            key: "TasteOfBlood",
            icon: "perk-images/Styles/Domination/TasteOfBlood/GreenTerror_TasteOfBlood.png",
            name: "피의 맛",
            shortDesc: "적 챔피언에게 피해를 입히면 체력을 회복합니다.",
            longDesc:
              "적 챔피언에게 피해를 입히면 체력을 회복합니다.<br><br>회복량: 16 ~ 30 (+추가 공격력의 0.15, +주문력의 0.08) (레벨에 비례)<br><br>재사용 대기시간: 20초",
          },
          {
            id: 8143,
            key: "SuddenImpact",
            icon: "perk-images/Styles/Domination/SuddenImpact/SuddenImpact.png",
            name: "돌발 일격",
            shortDesc:
              "돌진, 도약, 점멸, 순간이동을 사용하거나 은신에서 빠져나오면 물리 관통력 및 마법 관통력 급증",
            longDesc:
              "돌진, 도약, 점멸, 순간이동을 사용하거나 은신에서 빠져나온 뒤 적 챔피언에게 피해를 주면 5초 동안 7의 물리 관통력과 6의 마법 관통력을 얻습니다.<br><br>재사용 대기시간: 4초",
          },
        ],
      },
      {
        runes: [
          {
            id: 8136,
            key: "ZombieWard",
            icon: "perk-images/Styles/Domination/ZombieWard/ZombieWard.png",
            name: "좀비 와드",
            shortDesc:
              "적 와드 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>파괴 관여</lol-uikit-tooltipped-keyword> 시 그 자리에 아군 좀비 와드 생성. 생성된 좀비 와드 하나당 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'>적응형</lol-uikit-tooltipped-keyword>으로 공격력 또는 주문력 영구 증가 및 최대 개수 도달 시 추가로 증가",
            longDesc:
              "적 와드 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>파괴 관여</lol-uikit-tooltipped-keyword> 시 그 자리에 아군 좀비 와드가 생성됩니다.<br><br>좀비 와드가 생성될 때마다 최대 10회까지 중첩되는 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>적응형</font></lol-uikit-tooltipped-keyword>으로 추가 공격력 1.2 또는 추가 주문력 2의 효과를 얻습니다. <br><br>좀비 와드를 10개 생성하면 10의 적응형 능력치를 부가적으로 획득합니다.<br><br>좀비 와드는 적이 볼 수 있고 120초 동안 유지되며, 설치 가능 와드 수에 영향을 주지 않습니다.",
          },
          {
            id: 8120,
            key: "GhostPoro",
            icon: "perk-images/Styles/Domination/GhostPoro/GhostPoro.png",
            name: "유령 포로",
            shortDesc:
              "자신이 설치한 와드의 지속시간이 끝나면 유령 포로 와드가 남아 발견될 때까지 시야를 밝히고, 생성된 유령 포로 와드 하나당 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'>적응형</lol-uikit-tooltipped-keyword>으로 공격력 또는 주문력 영구 증가 및 유령 포로 와드가 적 챔피언 발견 시 추가로 증가",
            longDesc:
              "자신이 설치한 와드의 지속시간이 끝나면 유령 포로 와드가 남아 90초 동안 시야를 밝힙니다. 적 챔피언이 근처에 오면 유령 포로 와드를 몰아낼 수 있습니다.<br><br>유령 포로 와드가 생성되거나 유령 포로 와드가 적 챔피언을 발견할 때마다 최대 10회까지 중첩되는 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>적응형</font></lol-uikit-tooltipped-keyword>으로 추가 공격력 1.2 또는 추가 주문력 2의 효과를 얻습니다. <br><br>10회 중첩되면 10의 적응형 능력치를 부가적으로 획득합니다.",
          },
          {
            id: 8138,
            key: "EyeballCollection",
            icon: "perk-images/Styles/Domination/EyeballCollection/EyeballCollection.png",
            name: "사냥의 증표",
            shortDesc:
              "챔피언 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시마다 증표 획득. 개당 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'>적응형</lol-uikit-tooltipped-keyword>으로 공격력 또는 주문력 영구 증가 및 최대 개수 도달 시 추가 증가",
            longDesc:
              "챔피언 처치에 관여하면 증표를 얻습니다. 증표 하나당 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>적응형</font></lol-uikit-tooltipped-keyword>으로 추가 공격력 1.2 또는 추가 주문력 2의 효과를 얻습니다. <br><br>증표 개수가 최대치인 10개에 도달하면 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>적응형</font></lol-uikit-tooltipped-keyword>으로 추가 공격력 6 또는 추가 주문력 10의 효과를 부가적으로 획득합니다.<br><br>적 챔피언 처치 관여 시마다 증표 1개를 얻습니다.",
          },
        ],
      },
      {
        runes: [
          {
            id: 8135,
            key: "TreasureHunter",
            icon: "perk-images/Styles/Domination/TreasureHunter/TreasureHunter.png",
            name: "보물 사냥꾼",
            shortDesc:
              "<b>고유</b> <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시 처음으로 획득하는 골드량 증가 ",
            longDesc:
              "다음번 <i>현상금 사냥꾼</i> 중첩을 획득하면 <gold>70골드</gold>를 추가로 얻습니다. <i>현상금 사냥꾼</i> 중첩 하나당 골드 획득량이 <gold>20골드</gold>씩 증가합니다. (최대 <gold>150골드</gold>)<br><br>각 적 챔피언을 처치하는 데 처음으로 관여할 때마다 <i>현상금 사냥꾼</i> 중첩을 얻을 수 있습니다.",
          },
          {
            id: 8134,
            key: "IngeniousHunter",
            icon: "perk-images/Styles/Domination/IngeniousHunter/IngeniousHunter.png",
            name: "영리한 사냥꾼",
            shortDesc:
              "적 챔피언당 <b>1회</b> 한정 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시 영구적으로 아이템 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_CDR'>가속</lol-uikit-tooltipped-keyword> 증가 (장신구 포함)",
            longDesc:
              "<b>아이템 가속</b>이 <attention>20</attention>+<i>현상금 사냥꾼</i> 중첩 1회당 <attention>6</attention> 증가합니다. (장신구 포함)<br><br>각 적 챔피언을 처치하는 데 처음으로 관여할 때마다 <i>현상금 사냥꾼</i> 중첩을 얻을 수 있습니다.<br><br><rules>아이템 가속은 재사용 대기시간이 있는 모든 아이템에 적용됩니다. </rules>",
          },
          {
            id: 8105,
            key: "RelentlessHunter",
            icon: "perk-images/Styles/Domination/RelentlessHunter/RelentlessHunter.png",
            name: "끈질긴 사냥꾼",
            shortDesc:
              "적 챔피언당 <b>1회</b> 한정 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시 <b>전투에서 벗어나 있을 때 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>이동 속도</lol-uikit-tooltipped-keyword></b> 영구 증가 ",
            longDesc:
              "전투에서 벗어나 있을 때 <speed>이동 속도가 5</speed> 상승합니다. <i>현상금 사냥꾼</i> 중첩 하나당 <speed>8</speed>씩 추가됩니다.<br><br>각 적 챔피언을 처치하는 데 처음으로 관여할 때마다 <i>현상금 사냥꾼</i> 중첩을 얻을 수 있습니다.",
          },
          {
            id: 8106,
            key: "UltimateHunter",
            icon: "perk-images/Styles/Domination/UltimateHunter/UltimateHunter.png",
            name: "궁극의 사냥꾼",
            shortDesc:
              "적 챔피언당 <b>1회</b> 한정 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시 궁극기 재사용 대기시간 영구 감소 ",
            longDesc:
              "궁극기의 스킬 가속이 <attention>6</attention>+<i>현상금 사냥꾼</i> 중첩 1회당 <attention>5</attention> 증가합니다.<br><br>각 적 챔피언을 처치하는 데 처음으로 관여할 때마다 <i>현상금 사냥꾼</i> 중첩을 얻을 수 있습니다.",
          },
        ],
      },
    ],
  },
  {
    id: 8300,
    key: "Inspiration",
    icon: "perk-images/Styles/7203_Whimsy.png",
    name: "영감",
    slots: [
      {
        runes: [
          {
            id: 8351,
            key: "GlacialAugment",
            icon: "perk-images/Styles/Inspiration/GlacialAugment/GlacialAugment.png",
            name: "빙결 강화",
            shortDesc:
              "적 챔피언을 이동 불가 상태로 만들면 3줄기의 빙결 광선이 뻗어 나와 근처 적들을 둔화하고 아군에게 입히는 피해를 감소시킵니다.",
            longDesc:
              "적 챔피언을 이동 불가 상태로 만들면 대상에게서 3줄기의 빙결 광선이 자신과 근처 다른 챔피언을 향해 뻗어 나와 3초(+이동 불가 효과 지속시간의 100%) 동안 적들을 30%(+회복 및 보호막 효과 10%당 9%)(+주문력 100당 3%)(+추가 공격력 100당 4%) 둔화하고 자신을 제외한 아군에게 입히는 피해를 15% 감소시키는 빙결 영역을 생성합니다. <br><br>재사용 대기시간: 25초",
          },
          {
            id: 8360,
            key: "UnsealedSpellbook",
            icon: "perk-images/Styles/Inspiration/UnsealedSpellbook/UnsealedSpellbook.png",
            name: "봉인 풀린 주문서",
            shortDesc:
              "전투에서 벗어났을 때 소환사 주문 교환 가능. 새로운 소환사 주문으로 교환할 때마다 교환 재사용 대기시간 감소.",
            longDesc:
              "장착한 소환사 주문을 한 번만 사용 가능한 새로운 소환사 주문으로 교환합니다. 이전에 쓰지 않은 새로운 소환사 주문으로 교환할 때마다 교환의 재사용 대기시간이 영구적으로 25초씩 감소합니다. (최초 교환의 재사용 대기시간: 5분) <br><br>첫 번째 교환은 6분부터 가능합니다. <br><rules><br>소환사 주문은 전투에서 벗어났을 때만 교환할 수 있습니다. <br>교환한 소환사 주문을 사용한 후 3번 더 교환해야 다시 첫 번째 소환사 주문을 선택할 수 있습니다.<br>소환사 주문을 2회 교환한 후부터 강타 피해량이 증가합니다. </rules>",
          },
          {
            id: 8369,
            key: "FirstStrike",
            icon: "perk-images/Styles/Inspiration/FirstStrike/FirstStrike.png",
            name: "선제공격",
            shortDesc:
              "적 챔피언과 전투 개시 시 3초 동안 10%의 추가 피해 및 입힌 피해에 따라 골드 획득",
            longDesc:
              "적 챔피언과 전투 시작 후 0.25초 내에 스킬이나 기본 공격으로 해당 적 챔피언에게 피해를 입히면 5골드를 획득하고 3초 동안 <b>선제공격</b> 효과가 발동됩니다. 효과 발동 시 적 챔피언에게 <truedamage>10%</truedamage>의 추가 <truedamage>피해</truedamage>를 입힙니다. 또한 입힌 추가 피해의 <gold>100%(원거리 챔피언은 70%)</gold>에 해당하는 <gold>골드</gold>를 획득합니다.<br><br>재사용 대기시간: <scaleLevel>25~15</scaleLevel>초",
          },
        ],
      },
      {
        runes: [
          {
            id: 8306,
            key: "HextechFlashtraption",
            icon: "perk-images/Styles/Inspiration/HextechFlashtraption/HextechFlashtraption.png",
            name: "마법공학 점멸기",
            shortDesc:
              "점멸이 재사용 대기 중일 때 <i>마법공학 점멸</i>로 대체됨<br><br><i>마법공학 점멸</i>: 정신 집중 후 다른 지점으로 순간적으로 이동",
            longDesc:
              "점멸이 재사용 대기 중일 때 <i>마법공학 점멸</i>로 대체됩니다.<br><br><i>마법공학 점멸</i>: 2초간 정신을 집중한 뒤 다른 지점으로 도약합니다.<br><br>재사용 대기시간: 20초. 챔피언과 전투에 돌입할 경우 10초의 재사용 대기시간이 시작됩니다.",
          },
          {
            id: 8304,
            key: "MagicalFootwear",
            icon: "perk-images/Styles/Inspiration/MagicalFootwear/MagicalFootwear.png",
            name: "마법의 신발",
            shortDesc:
              "게임 시작 후 12분에 장화 무료 획득. 그 전까지 신발류 아이템 구매 불가. 챔피언 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시마다 장화 획득 시점이 45초 앞당겨짐",
            longDesc:
              "게임 시작 12분 후 약간 신비한 신발 아이템을 얻습니다. 그 전까지는 신발류 아이템을 구매할 수 없습니다. 챔피언 처치에 관여할 때마다 장화 획득 시점이 45초씩 앞당겨집니다.<br><br>약간 신비한 신발 아이템으로 <speed>이동 속도가 10</speed> 증가합니다.",
          },
          {
            id: 8313,
            key: "PerfectTiming",
            icon: "perk-images/Styles/Inspiration/PerfectTiming/PerfectTiming.png",
            name: "완벽한 타이밍",
            shortDesc:
              "초시계 키트 획득. 14분 후부터 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Stasis'>경직</lol-uikit-tooltipped-keyword> 효과 1회 사용 가능 <br><br><lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시마다 사용 가능 시점 120초씩 단축",
            longDesc:
              "게임 시작 시 초시계 키트 아이템을 받습니다. 이 아이템은 14분 후 초시계 아이템으로 변합니다. 챔피언 처치 관여 시마다 이 시간이 120초 감소합니다.<br><br>초시계 아이템은 단 한 번 사용할 수 있으며, 사용 시 2.5초 동안 경직 효과를 얻습니다.",
          },
        ],
      },
      {
        runes: [
          {
            id: 8321,
            key: "FuturesMarket",
            icon: "perk-images/Styles/Inspiration/FuturesMarket/FuturesMarket.png",
            name: "외상",
            shortDesc: "아이템 외상 구매 가능",
            longDesc:
              "외상으로 아이템을 구입할 수 있습니다. 외상 한도는 점차 증가합니다.<br><br>외상 비용: 50골드",
          },
          {
            id: 8316,
            key: "MinionDematerializer",
            icon: "perk-images/Styles/Inspiration/MinionDematerializer/MinionDematerializer.png",
            name: "미니언 해체분석기",
            shortDesc:
              "미니언 해체분석기 3개를 보유하고 게임 시작. 해당 아이템으로 미니언 처치 시 동일한 종류의 미니언에게 추가 피해 적용",
            longDesc:
              "게임 시작 시, 공격로 미니언을 즉시 처치하고 흡수하는 미니언 해체분석기 3개를 받습니다. 미니언 해체분석기 아이템은 게임 시작 후 180초 동안 재사용 대기 상태입니다.<br><br>이 아이템으로 미니언을 흡수하면 이후 동일한 종류의 미니언을 대상으로 6%의 추가 피해를 입히며, 동일한 종류의 미니언을 추가 흡수 시마다 3%의 추가 피해를 입힙니다.<br>",
          },
          {
            id: 8345,
            key: "BiscuitDelivery",
            icon: "perk-images/Styles/Inspiration/BiscuitDelivery/BiscuitDelivery.png",
            name: "비스킷 배달",
            shortDesc:
              "게임 시작 후 6분까지 2분마다 비스킷 1개 획득. 비스킷 사용 또는 판매 시 체력/마나 회복 및 최대 마나 영구 증가",
            longDesc:
              "비스킷 배달: 6분까지 2분마다 굳건한 의지의 완전한 비스킷 아이템을 얻습니다.<br><br>비스킷을 사용하면 잃은 체력과 마나의 10%를 회복합니다. 비스킷 하나를 사용하거나 판매하면 최대 마나가 영구적으로 50만큼 늘어납니다. <br><br><i>마나 없음:</i> 마나가 없는 챔피언은 마나 대신 잃은 체력의 12%를 회복합니다.",
          },
        ],
      },
      {
        runes: [
          {
            id: 8347,
            key: "CosmicInsight",
            icon: "perk-images/Styles/Inspiration/CosmicInsight/CosmicInsight.png",
            name: "우주적 통찰력",
            shortDesc:
              "소환사 주문 가속 +<attention>18</attention><br>아이템 가속 +<attention>10</attention>",
            longDesc:
              "소환사 주문 가속 +<attention>18</attention><br>아이템 가속 +<attention>10</attention>",
          },
          {
            id: 8410,
            key: "ApproachVelocity",
            icon: "perk-images/Styles/Resolve/ApproachVelocity/ApproachVelocity.png",
            name: "쾌속 접근",
            shortDesc:
              "<lol-uikit-tooltipped-keyword key='LinkTooltip_Description_ImpairMov'>이동을 방해받은</lol-uikit-tooltipped-keyword> 근처 적 챔피언에게 이동할 때 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>이동 속도</lol-uikit-tooltipped-keyword> 증가. 자신이 이동 방해 스킬을 맞힌 적에게 이동할 때는 추가 증가",
            longDesc:
              "이동 방해 스킬에 맞은 근처의 적 챔피언에게 이동할 때 <speed>이동 속도가 7.5%</speed> 증가합니다. 적 챔피언에게 이동 방해 스킬을 맞히고 해당 적에게 이동할 때는 <speed>이동 속도가 15%</speed>까지 증가합니다. <br><br>아군 군중 제어 사용 범위: 1000",
          },
          {
            id: 8352,
            key: "TimeWarpTonic",
            icon: "perk-images/Styles/Inspiration/TimeWarpTonic/TimeWarpTonic.png",
            name: "시간 왜곡 물약",
            shortDesc:
              "물약이나 비스킷으로 회복량 일부를 즉시 회복. 물약이나 비스킷의 효과가 지속되는 동안 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>이동 속도</lol-uikit-tooltipped-keyword> 증가",
            longDesc:
              "물약이나 비스킷을 사용하면 체력이나 마나 회복량의 30%를 즉시 회복합니다. 또한 물약이나 비스킷의 효과가 지속되는 동안 <speed>이동 속도가 4%</speed> 증가합니다.<br><br>",
          },
        ],
      },
    ],
  },
  {
    id: 8000,
    key: "Precision",
    icon: "perk-images/Styles/7201_Precision.png",
    name: "정밀",
    slots: [
      {
        runes: [
          {
            id: 8005,
            key: "PressTheAttack",
            icon: "perk-images/Styles/Precision/PressTheAttack/PressTheAttack.png",
            name: "집중 공격",
            shortDesc:
              "적 챔피언을 연속 3회 기본 공격하면 적의 약점이 노출되며 추가 피해를 줌. 약점이 노출된 적은 6초 동안 모든 상대에게서 추가 피해를 받음",
            longDesc:
              "적 챔피언에게 연속으로 3회 기본 공격을 가하면 레벨에 따라 40 ~ 180의 추가 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'><font color='#48C4B7'>적응형 피해</font></lol-uikit-tooltipped-keyword>를 입히고 적의 약점을 노출시킵니다. 약점이 노출된 적은 6초 동안 모든 상대에게서 8 ~ 12%의 추가 피해를 받습니다.",
          },
          {
            id: 8008,
            key: "LethalTempo",
            icon: "perk-images/Styles/Precision/LethalTempo/LethalTempoTemp.png",
            name: "치명적 속도",
            shortDesc:
              "적 챔피언 기본 공격 시 공격 속도 상승(최대 6회 중첩). 최대 중첩 시 공격 사거리 상승 및 최고 공격 속도 제한 해제",
            longDesc:
              "적 챔피언을 기본 공격하면 6초 동안 공격 속도가 [60%~90%] (근접) 또는 [30%~54%] (원거리) 상승합니다. 이 효과는 6회까지 중첩됩니다.<br><br>최대로 중첩되면 공격 속도가 2.5를 초과할 수 있으며 공격 사거리가 50 (근접) 또는 75 (원거리) 상승합니다.",
          },
          {
            id: 8021,
            key: "FleetFootwork",
            icon: "perk-images/Styles/Precision/FleetFootwork/FleetFootwork.png",
            name: "기민한 발놀림",
            shortDesc:
              "공격 및 이동 시 충전. 충전 중첩 100회 상태로 공격 시 체력 회복 및 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>이동 속도</lol-uikit-tooltipped-keyword> 증가",
            longDesc:
              "공격 또는 이동 시 충전 중첩이 쌓입니다. 중첩이 100회에 도달하면 충전 상태로 다음 공격을 할 수 있습니다.<br><br>충전 상태로 공격 시 10~100(+추가 공격력의 0.3, +주문력의 0.2)에 해당하는 체력이 회복되며 1초 동안 <speed>이동 속도가 20%</speed> 증가합니다.<br><br>미니언으로부터 받는 회복 효과는 원거리 챔피언의 경우 10%, 근접 챔피언의 경우 20% 적용됩니다.",
          },
          {
            id: 8010,
            key: "Conqueror",
            icon: "perk-images/Styles/Precision/Conqueror/Conqueror.png",
            name: "정복자",
            shortDesc:
              "적 챔피언 공격 시 적응형 능력치 중첩 획득. 12회 중첩 시 챔피언 대상 피해량의 일부만큼 체력 회복",
            longDesc:
              "적 챔피언에게 기본 공격 또는 스킬로 피해를 입히면 5초 동안 정복자 중첩을 2만큼 얻어 중첩마다 2~4.5의 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>적응형 능력치</font></lol-uikit-tooltipped-keyword>를 얻습니다. 최대 12회까지 중첩됩니다. 원거리 챔피언은 기본 공격으로 중첩을 1만 획득할 수 있습니다.<br><br>최대로 중첩되면 챔피언에게 입힌 피해량의 8%만큼 체력을 회복합니다. (원거리 챔피언은 5%)",
          },
        ],
      },
      {
        runes: [
          {
            id: 9101,
            key: "Overheal",
            icon: "perk-images/Styles/Precision/Overheal.png",
            name: "과다치유",
            shortDesc: "자신의 체력을 초과한 체력 회복 효과가 보호막으로 전환",
            longDesc:
              "자신에 대한 체력 회복 초과분이 보호막으로 전환됩니다. 보호막은 10<scaleHealth>(+최대 체력의 9%)</scaleHealth>에 해당하는 피해까지 흡수할 수 있습니다.<br><br>보호막 전환율은 자신 또는 아군으로부터의 체력 회복 초과분의 20~100%입니다.",
          },
          {
            id: 9111,
            key: "Triumph",
            icon: "perk-images/Styles/Precision/Triumph.png",
            name: "승전보",
            shortDesc:
              "챔피언 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시 잃은 체력의 10%를 회복하며 추가로 20골드 획득 ",
            longDesc:
              "챔피언 처치 또는 어시스트 시 잃은 체력의 10%를 회복하며 추가로 20골드를 획득합니다. <br><br><hr><br><i>'가장 위험한 게임을 하는 자만이 진정 승리의 환희를 맛보았다고 말할 수 있다.' <br>—녹서스 검투사</i>",
          },
          {
            id: 8009,
            key: "PresenceOfMind",
            icon: "perk-images/Styles/Precision/PresenceOfMind/PresenceOfMind.png",
            name: "침착",
            shortDesc:
              "적 챔피언에게 피해를 입히면 마나 또는 기력 재생량 증가. 처치 관여 시 마나 또는 기력 회복",
            longDesc:
              "적 챔피언에게 피해를 입히면 4초 동안 초당 마나 재생이 @RegenAmount@ (원거리 챔피언은 80%) 증가합니다. 기력 기반 챔피언은 초당 1.5의 기력을 얻습니다.<br><br>처치 관여 시 최대 마나 또는 기력의 15%를 돌려받습니다. ",
          },
        ],
      },
      {
        runes: [
          {
            id: 9104,
            key: "LegendAlacrity",
            icon: "perk-images/Styles/Precision/LegendAlacrity/LegendAlacrity.png",
            name: "전설: 민첩함",
            shortDesc:
              "적 챔피언 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시 영구적으로 <b>공격 속도</b> 증가 ",
            longDesc:
              "공격 속도가 3% 증가하며 <i>전설</i> 중첩당 1.5%의 공격 속도가 추가로 증가합니다. (<statGood>최대 전설 중첩 횟수: 10</statGood>)<br><br>챔피언 처치 관여, 에픽 몬스터 처치 관여, 대형 몬스터 처치, 미니언 처치 시마다 <i>전설</i> 중첩을 얻습니다.",
          },
          {
            id: 9105,
            key: "LegendTenacity",
            icon: "perk-images/Styles/Precision/LegendTenacity/LegendTenacity.png",
            name: "전설: 강인함",
            shortDesc:
              "적 챔피언 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시 영구적으로 <b>강인함</b> 효과 획득 ",
            longDesc:
              "강인함이 5% 증가하며 <i>전설</i> 중첩당 2.5%의 강인함이 추가로 증가합니다. (<statGood>최대 전설 중첩 횟수: 10</statGood>)<br><br>챔피언 처치 관여, 에픽 몬스터 처치 관여, 대형 몬스터 처치, 미니언 처치 시마다 <i>전설</i> 중첩을 얻습니다.",
          },
          {
            id: 9103,
            key: "LegendBloodline",
            icon: "perk-images/Styles/Precision/LegendBloodline/LegendBloodline.png",
            name: "전설: 핏빛 길",
            shortDesc:
              "적 챔피언 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Takedown'>처치 관여</lol-uikit-tooltipped-keyword> 시 영구적으로 <b>생명력 흡수</b> 효과 획득. (최대치 있음) 최대치 도달 시 최대 체력 증가. 다른 전설 룬에 비해 게임 초반에 약하고 후반에 강한 룬",
            longDesc:
              "<i>전설</i> 중첩당 생명력 흡수가 0.4% 증가합니다. (<statGood>최대 전설 중첩 횟수: 15</statGood>) 최대 <i>전설</i> 중첩 시 최대 체력이 100 증가합니다.<br><br>챔피언 처치 관여, 에픽 몬스터 처치 관여, 대형 몬스터 처치, 미니언 처치 시마다 <i>전설</i> 중첩을 얻습니다.",
          },
        ],
      },
      {
        runes: [
          {
            id: 8014,
            key: "CoupDeGrace",
            icon: "perk-images/Styles/Precision/CoupDeGrace/CoupDeGrace.png",
            name: "최후의 일격",
            shortDesc: "체력이 낮은 적 챔피언에게 입히는 피해량 증가",
            longDesc:
              "체력이 40% 이하인 적 챔피언에게 주는 피해량이 8% 증가합니다.",
          },
          {
            id: 8017,
            key: "CutDown",
            icon: "perk-images/Styles/Precision/CutDown/CutDown.png",
            name: "체력차 극복",
            shortDesc: "적 최대 체력이 더 많을 경우 해당 챔피언에게 추가 피해",
            longDesc:
              "적 챔피언의 최대 체력이 자신보다 많은 정도에 비례해 해당 챔피언에게 5%~15%의 추가 피해를 입힙니다.<br><br><rules>추가 피해량은 적의 최대 체력이 10%~100% 더 많을 경우 그에 따라 증가합니다.</rules>",
          },
          {
            id: 8299,
            key: "LastStand",
            icon: "perk-images/Styles/Sorcery/LastStand/LastStand.png",
            name: "최후의 저항",
            shortDesc: "체력이 낮을 때 적 챔피언 공격 시 추가 피해",
            longDesc:
              "체력이 60% 이하일 때 적 챔피언 공격 시 5% ~ 11%의 추가 피해를 줍니다. 체력이 30%일 때 최대 피해량에 도달합니다.",
          },
        ],
      },
    ],
  },
  {
    id: 8400,
    key: "Resolve",
    icon: "perk-images/Styles/7204_Resolve.png",
    name: "결의",
    slots: [
      {
        runes: [
          {
            id: 8437,
            key: "GraspOfTheUndying",
            icon: "perk-images/Styles/Resolve/GraspOfTheUndying/GraspOfTheUndying.png",
            name: "착취의 손아귀",
            shortDesc:
              "4초마다 적 챔피언 기본 공격 시 추가 마법 피해, 체력 회복, 체력 영구 증가",
            longDesc:
              "전투 중 4초마다 챔피언에 대한 기본 공격 시 다음 효과를 얻습니다.<li>자신 최대 체력의 3.5%에 해당하는 추가 마법 피해</li><li>자신 최대 체력의 1.7%에 해당하는 체력 회복</li><li>영구적으로 체력 5 증가</li><br><rules><i>원거리 챔피언:</i> 피해량, 회복량, 체력 영구 증가량이 40% 감소합니다.</rules><br>",
          },
          {
            id: 8439,
            key: "Aftershock",
            icon: "perk-images/Styles/Resolve/VeteranAftershock/VeteranAftershock.png",
            name: "여진",
            shortDesc:
              "적 챔피언을 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Immobilize'>이동 불가</lol-uikit-tooltipped-keyword> 상태로 만들면 방어력/마법 저항력 증가 및 잠시 후 주변에 큰 마법 피해",
            longDesc:
              "적을 이동 불가 상태로 만들면 2.5초 동안 35+추가 방어력 및 마법 저항력의 80%만큼 방어력과 마법 저항력이 증가하며 폭발이 일어나 근처 적에게 마법 피해를 줍니다.<br><br>피해량: 25~120 (+추가 체력의 8%)<br>재사용 대기시간: 20초<br><br>여진 추가 저항력: 80~150 (레벨에 비례)<br>",
          },
          {
            id: 8465,
            key: "Guardian",
            icon: "perk-images/Styles/Resolve/Guardian/Guardian.png",
            name: "수호자",
            shortDesc:
              "스킬 대상으로 지정한 아군이나 근처에 있는 아군을 보호. 자신 또는 보호받는 아군이 레벨에 비례하여 피해를 입으면 둘 다 보호막 획득.",
            longDesc:
              "자신으로부터 350유닛 내에 있는 아군이나 자신이 스킬의 대상으로 삼은 아군을 2.5초 동안 <i>보호</i>합니다. <i>보호</i>하는 중 자신과 아군 중 한 명이 <i>보호</i>의 지속시간 동안 어느 정도 피해를 입으면 둘 모두에게 1.5초 동안 보호막이 생성됩니다.<br><br>재사용 대기시간: <scaleLevel>70~40</scaleLevel>초<br>보호막 흡수량: <scaleLevel>45~120</scaleLevel>+주문력의 <scaleAP>12.5%</scaleAP>+추가 체력의 <scalehealth>8%</scalehealth><br>최종 기준치: 감소 후 피해량 <scaleLevel>90~250</scaleLevel>",
          },
        ],
      },
      {
        runes: [
          {
            id: 8446,
            key: "Demolish",
            icon: "perk-images/Styles/Resolve/Demolish/Demolish.png",
            name: "철거",
            shortDesc: "포탑 근처에서 포탑에 대한 강력한 공격을 충전",
            longDesc:
              "포탑으로부터 600 범위 안에 있을 경우 3초에 걸쳐 포탑에 대한 강력한 공격을 충전합니다. 충전된 공격은 100(+최대 체력의 35%)에 해당하는 추가 물리 피해를 입힙니다. <br><br>재사용 대기시간: 45초",
          },
          {
            id: 8463,
            key: "FontOfLife",
            icon: "perk-images/Styles/Resolve/FontOfLife/FontOfLife.png",
            name: "생명의 샘",
            shortDesc:
              "적 챔피언 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_ImpairMov'>이동 방해</lol-uikit-tooltipped-keyword> 시 표식을 남겨, 아군이 해당 적 공격 시 체력 회복 ",
            longDesc:
              "적 챔피언의 이동을 방해하면 4초 동안 표식을 남깁니다.<br><br>표식이 남겨진 적을 공격하는 아군 챔피언은 2초에 걸쳐 5 + 나의 최대 체력의 0.9%에 해당하는 체력을 회복합니다. ",
          },
          {
            id: 8401,
            key: "ShieldBash",
            icon: "perk-images/Styles/Resolve/MirrorShell/MirrorShell.png",
            name: "보호막 강타",
            shortDesc:
              "보호막을 얻으면 적 챔피언에게 다음 기본 공격 시 추가 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>적응형</font></lol-uikit-tooltipped-keyword> 피해",
            longDesc:
              "보호막이 지속되는 동안 레벨에 따라 <scaleLevel>1~10</scaleLevel>의 방어력과 마법 저항력을 얻습니다.<br><br>새로운 보호막을 얻으면 적 챔피언에게 다음 기본 공격 시 <scaleLevel>5~30</scaleLevel><scaleHealth>(+추가 체력의 1.5%)</scaleHealth><scaleMana>(+새로운 보호막 흡수량의 8.5%)</scaleMana>에 해당하는 추가 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>적응형</font></lol-uikit-tooltipped-keyword> 피해를 입힙니다.<br><br>이 효과는 보호막이 사라진 후 최대 2초까지 발동 가능합니다.",
          },
        ],
      },
      {
        runes: [
          {
            id: 8429,
            key: "Conditioning",
            icon: "perk-images/Styles/Resolve/Conditioning/Conditioning.png",
            name: "사전 준비",
            shortDesc:
              "12분 후 방어력 +9 및 마법 저항력 +9 증가와 동시에 방어력 및 마법 저항력 4% 증가",
            longDesc:
              "12분 후 방어력 +9 및 마법 저항력 +9 증가와 동시에 방어력 및 마법 저항력 4% 증가",
          },
          {
            id: 8444,
            key: "SecondWind",
            icon: "perk-images/Styles/Resolve/SecondWind/SecondWind.png",
            name: "재생의 바람",
            shortDesc:
              "적 챔피언에게 피해를 입으면 일정 시간에 걸쳐 잃은 체력의 일부 회복 ",
            longDesc:
              "적 챔피언에게 피해를 입으면 10초 동안 잃은 체력의 4% + 6만큼 회복합니다.",
          },
          {
            id: 8473,
            key: "BonePlating",
            icon: "perk-images/Styles/Resolve/BonePlating/BonePlating.png",
            name: "뼈 방패",
            shortDesc:
              "적 챔피언으로부터 피해를 입은 뒤 해당 적이 가하는 3회의 스킬 및 기본 공격으로부터 30~60만큼 피해를 덜 받습니다.<br><br><br>지속시간: 1.5초<br>재사용 대기시간: 45초",
            longDesc:
              "적 챔피언으로부터 피해를 입은 뒤 해당 적이 가하는 3회의 스킬 및 기본 공격으로부터 30~60만큼 피해를 덜 받습니다.<br><br><br>지속시간: 1.5초<br>재사용 대기시간: 45초",
          },
        ],
      },
      {
        runes: [
          {
            id: 8451,
            key: "Overgrowth",
            icon: "perk-images/Styles/Resolve/Overgrowth/Overgrowth.png",
            name: "과잉성장",
            shortDesc:
              "근처에서 몬스터 또는 미니언이 죽을 때마다 최대 체력 영구 증가",
            longDesc:
              "근처에서 몬스터 또는 적 미니언이 8마리 죽을 때마다 생명의 정수를 흡수해 최대 체력이 영구적으로 3씩 증가합니다.<br><br>몬스터 또는 적 미니언을 120마리 흡수하면 최대 체력이 추가로 3.5% 증가합니다.",
          },
          {
            id: 8453,
            key: "Revitalize",
            icon: "perk-images/Styles/Resolve/Revitalize/Revitalize.png",
            name: "소생",
            shortDesc:
              "보호막 및 체력 회복 5% 증가<br><br>체력이 40% 이하인 대상에게는 자신이 사용하거나 받는 회복과 보호막 효과가 10% 강화",
            longDesc:
              "보호막 및 체력 회복 효과가 5% 증가합니다.<br><br>체력이 40% 이하인 대상에게는 자신이 사용하거나 받는 회복과 보호막 효과가 10% 강화됩니다.",
          },
          {
            id: 8242,
            key: "Unflinching",
            icon: "perk-images/Styles/Sorcery/Unflinching/Unflinching.png",
            name: "불굴의 의지",
            shortDesc: "잃은 체력에 비례하여 상승하는 강인함 효과 소폭 증가 ",
            longDesc:
              "강인함이 10%, 둔화 저항이 10% 증가합니다. 잃은 체력에 비례하여 강인함은 20%, 둔화 저항은 20% 추가로 증가합니다. 체력이 30%일 때 최대로 증가합니다. ",
          },
        ],
      },
    ],
  },
  {
    id: 8200,
    key: "Sorcery",
    icon: "perk-images/Styles/7202_Sorcery.png",
    name: "마법",
    slots: [
      {
        runes: [
          {
            id: 8214,
            key: "SummonAery",
            icon: "perk-images/Styles/Sorcery/SummonAery/SummonAery.png",
            name: "콩콩이 소환",
            shortDesc:
              "공격 또는 스킬 사용 시 대상에 콩콩이를 보내 적에게 피해를 주거나 아군에게 보호막 생성",
            longDesc:
              "적 챔피언을 기본 공격 또는 스킬로 공격하면 콩콩이를 보내 레벨에 따라 10~40(+<scaleAP>주문력의 0.1</scaleAP>)(+<scaleAD>추가 공격력의 0.15</scaleAD>)만큼 피해를 입힙니다.<br><br>아군에게 스킬로 강화 효과 또는 보호막을 적용하면 콩콩이를 보내 레벨에 따라 30~75(+<scaleAP>주문력의 0.22</scaleAP>)(+<scaleAD>추가 공격력의 0.35</scaleAD>)만큼 피해를 흡수하는 보호막을 씌웁니다.<br><br>콩콩이는 자신에게 돌아오기 전까지 다른 대상에게 보낼 수 없습니다.",
          },
          {
            id: 8229,
            key: "ArcaneComet",
            icon: "perk-images/Styles/Sorcery/ArcaneComet/ArcaneComet.png",
            name: "신비로운 유성",
            shortDesc:
              "챔피언에게 스킬로 피해를 주면 해당 위치에 피해를 가하는 유성 소환",
            longDesc:
              "챔피언에게 스킬로 피해를 주면 해당 위치에 유성을 불러옵니다. 신비로운 유성이 재사용 대기 중일 경우 남은 재사용 대기시간이 감소합니다.<br><br><lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'><font color='#48C4B7'>적응형 피해</font></lol-uikit-tooltipped-keyword>: 레벨에 따라 30 ~ 100 (<scaleAP>+주문력의 0.2</scaleAP> 및 <scaleAD>+추가 공격력의 0.35</scaleAD>)<br>재사용 대기시간: 20 ~ 8초<br><rules><br>재사용 대기시간 감소:<br>단일 대상 스킬: 20%<br>광역 스킬: 10%<br>지속 피해 스킬: 5%<br></rules>",
          },
          {
            id: 8230,
            key: "PhaseRush",
            icon: "perk-images/Styles/Sorcery/PhaseRush/PhaseRush.png",
            name: "난입",
            shortDesc:
              "한 챔피언에게 기본 공격 또는 <b>개별</b> 스킬 3회 적중 시 일시적으로 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>이동 속도</lol-uikit-tooltipped-keyword> 증가 ",
            longDesc:
              "4초 안에 한 챔피언에게 기본 공격 또는 <b>개별</b> 스킬 3회를 적중시키면 레벨에 따라 <speed>15~40%의 이동 속도</speed>와 75%의 둔화 저항 효과를 얻습니다. <hr>근접 챔피언의 경우 <speed>이동 속도가 30~60%</speed>까지 증가합니다.<hr>지속시간: 3초<br>재사용 대기시간: 10~30초",
          },
        ],
      },
      {
        runes: [
          {
            id: 8224,
            key: "NullifyingOrb",
            icon: "perk-images/Styles/Sorcery/NullifyingOrb/Pokeshield.png",
            name: "무효화 구체",
            shortDesc:
              "마법 피해를 받아 체력이 낮아지면 마법 피해를 흡수하는 보호막 생성",
            longDesc:
              "마법 피해를 받아 체력이 30% 이하가 될 경우, 4초 동안 레벨에 비례해 35 ~ 110 (<scaleAP>+주문력의 0.09</scaleAP> 및 <scaleAD>+추가 공격력의 0.14</scaleAD>)의 마법 피해를 흡수하는 보호막이 생성됩니다.<br><br>재사용 대기시간: 60초",
          },
          {
            id: 8226,
            key: "ManaflowBand",
            icon: "perk-images/Styles/Sorcery/ManaflowBand/ManaflowBand.png",
            name: "마나순환 팔찌",
            shortDesc:
              "적 챔피언에게 스킬을 적중하면 최대 마나가 영구적으로 25만큼 증가합니다. (최대 마나량: 250)<br><br>최대 마나량 250에 도달하면 5초마다 잃은 마나의 1%를 회복합니다.",
            longDesc:
              "적 챔피언에게 스킬을 적중하면 최대 마나가 영구적으로 25만큼 증가합니다. (최대 마나량: 250)<br><br>최대 마나량 250에 도달하면 5초마다 잃은 마나의 1%를 회복합니다.<br><br>재사용 대기시간: 15초",
          },
          {
            id: 8275,
            key: "NimbusCloak",
            icon: "perk-images/Styles/Sorcery/NimbusCloak/6361.png",
            name: "빛의 망토",
            shortDesc:
              "소환사 주문 사용 후 잠시 동안 <speed>이동 속도</speed> 증가 및 유닛 통과 가능",
            longDesc:
              "소환사 주문 사용 후 2초 동안 <speed>이동 속도</speed>가 증가하고 유닛을 통과할 수 있습니다.<br><br>증가: 소환사 주문 재사용 대기시간에 따라 <speed>이동 속도가 5%~25%</speed> 증가합니다. (재사용 대기시간이 길수록 <speed>이동 속도</speed>가 더 많이 증가합니다.) ",
          },
        ],
      },
      {
        runes: [
          {
            id: 8210,
            key: "Transcendence",
            icon: "perk-images/Styles/Sorcery/Transcendence/Transcendence.png",
            name: "깨달음",
            shortDesc:
              "다음 레벨이 되면 추가 효과 획득:<br>5레벨: <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_CDR'>스킬 가속</lol-uikit-tooltipped-keyword> +5 <br>8레벨: <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_CDR'>스킬 가속</lol-uikit-tooltipped-keyword> +5 <br>11레벨: 챔피언 처치 관여 시 기본 스킬의 남은 재사용 대기시간 20% 감소",
            longDesc:
              "다음 레벨이 되면 추가 효과 획득:<br>5레벨: <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_CDR'>스킬 가속</lol-uikit-tooltipped-keyword> +5 <br>8레벨: <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_CDR'>스킬 가속</lol-uikit-tooltipped-keyword> +5 <br>11레벨: 챔피언 처치 관여 시 기본 스킬의 남은 재사용 대기시간 20% 감소<br>",
          },
          {
            id: 8234,
            key: "Celerity",
            icon: "perk-images/Styles/Sorcery/Celerity/CelerityTemp.png",
            name: "기민함",
            shortDesc:
              "모든 추가 <speed>이동 속도</speed> 효과가 7% 증가하고 <speed>이동 속도 1%</speed> 증가",
            longDesc:
              "모든 추가 이동 속도 효과가 7% 증가하고 <speed>이동 속도를 1%</speed> 추가로 얻습니다.",
          },
          {
            id: 8233,
            key: "AbsoluteFocus",
            icon: "perk-images/Styles/Sorcery/AbsoluteFocus/AbsoluteFocus.png",
            name: "절대 집중",
            shortDesc:
              "체력이 70% 이상일 경우 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_AdaptiveDmg'>적응형 피해</lol-uikit-tooltipped-keyword> 추가 적용",
            longDesc:
              "체력이 70% 이상일 경우 레벨에 따라 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>적응형</font></lol-uikit-tooltipped-keyword>으로 추가 공격력 최대 18 또는 추가 주문력 최대 30의 효과를 얻습니다. <br><br>1레벨에 1.8의 공격력 또는 3의 주문력을 얻습니다. ",
          },
        ],
      },
      {
        runes: [
          {
            id: 8237,
            key: "Scorch",
            icon: "perk-images/Styles/Sorcery/Scorch/Scorch.png",
            name: "주문 작열",
            shortDesc: "10초마다 공격 스킬 적중 시 챔피언을 불태움",
            longDesc:
              "다음 공격 스킬 적중 시 챔피언에게 불을 붙여 1초 후 레벨에 따라 15~35의 추가 마법 피해를 줍니다.<br><br>재사용 대기시간: 10초",
          },
          {
            id: 8232,
            key: "Waterwalking",
            icon: "perk-images/Styles/Sorcery/Waterwalking/Waterwalking.png",
            name: "물 위를 걷는 자",
            shortDesc:
              "강에 있을 때 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_MS'>이동 속도</lol-uikit-tooltipped-keyword>를 얻고 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'>적응형</lol-uikit-tooltipped-keyword>으로 주문력 또는 공격력 증가",
            longDesc:
              "강에 있을 때 <speed>이동 속도가 25</speed> 증가하고 레벨에 비례하여 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>적응형</font></lol-uikit-tooltipped-keyword>으로 추가 공격력 최대 18 또는 추가 주문력 최대 30의 효과를 얻습니다.<br><br><hr><br><i>굽이치는 강물처럼 빠르고 깜짝 놀란 협곡 바위 게처럼 날렵하게 움직이리라.</i><br>",
          },
          {
            id: 8236,
            key: "GatheringStorm",
            icon: "perk-images/Styles/Sorcery/GatheringStorm/GatheringStorm.png",
            name: "폭풍의 결집",
            shortDesc:
              "게임이 진행됨에 따라 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'>적응형</lol-uikit-tooltipped-keyword> 공격력 또는 주문력 획득량 증가",
            longDesc:
              "10분마다 <lol-uikit-tooltipped-keyword key='LinkTooltip_Description_Adaptive'><font color='#48C4B7'>적응형</font></lol-uikit-tooltipped-keyword>으로 주문력 또는 공격력을 얻습니다.<br><br><i>10분</i>: + 주문력 8 또는 공격력 5 <br><i>20분</i>: + 주문력 24 또는 공격력 14<br><i>30분</i>: + 주문력 48 또는 공격력 29<br><i>40분</i>: + 주문력 80 또는 공격력 48<br><i>50분</i>: + 주문력 120 또는 공격력 72<br><i>60분</i>: + 주문력 168 또는 공격력 101<br>등등...",
          },
        ],
      },
    ],
  },
];
