Before updating, please **back-up** your world. Greatly appreciate any bug reports on the
[Github](https://github.com/ChefMooon/colourful-clocks/issues), I'll be
actively watching the issues page to resolve any bugs as soon as possible.

### Added
- New Clock Type:
  - Mantel Clock: Can customize the glass, clock hands(pocket watch), and can tick
- Bornholm Dials can now make a ticking sound
  - Right-click with redstone enable ticking (consumes redstone)
  - Right-click with a pickaxe to disable ticking (uses durability)
  - Can be disabled with a redstone signal
  - Clock must be assembled properly with trunk and base below
- Recipes for glass variants of the Bornholm Trunk and Dial 
- Item models now show glass type
- Pick Block now collects Trunk and Dial Glass Data
- Glass types now use translatable item name translations
- Silk Touch now retains glass type, pendulum, and dial
  - Trunk now shows Pendulum in tooltip if present
  - Dial now shows Dial in tooltip if present
- DataComponents for Trunk and Dial
  - Trunk: glass, pendulum
  - Dial: glass, dial

### Changed
- Pocket Watches can now be stacked to 64
- Updated Bornholm Dial Block Model
  - Hour marker sizes adjusted
  - Center marker added
  - Clock now has 2 hands: minute and hour
- General Improvements to the Bornholm Trunk and Dial models
- Adjusted Copper Pendulum swing speeds(Also Waxed)
  - Exposed Copper: Full Speed -> 3/4 Speed
  - Weathered Copper: Full Speed -> 1/2 Speed
  - Oxidized Copper: Full Speed -> 1/4 Speed
- Trunk and Dial tooltips for glass type now use translatable vanilla item name translations
- Jade/WTHIT Trunk and Dial tooltips for glass type now use translatable vanilla item name translations

### Fixed
- Fixed incorrect translation for the colourfulclocks:clock_pendulum tag