package world.bentobox.acidisland;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;

import world.bentobox.bentobox.api.configuration.ConfigComment;
import world.bentobox.bentobox.api.configuration.ConfigEntry;
import world.bentobox.bentobox.api.configuration.StoreAt;
import world.bentobox.bentobox.api.configuration.WorldSettings;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.database.objects.adapters.Adapter;
import world.bentobox.bentobox.database.objects.adapters.FlagSerializer;
import world.bentobox.bentobox.database.objects.adapters.FlagSerializer2;
import world.bentobox.bentobox.database.objects.adapters.PotionEffectListAdapter;

/**
 * Settings for AcidIsland
 * @author tastybento
 *
 */
@ConfigComment("AcidIsland [version] 配置文件")
@StoreAt(filename="config.yml", path="addons/AcidIsland") // Explicitly call out what name this should have.
public class AISettings implements WorldSettings {

    // ---------------------------------------------

    // Command
    @ConfigComment("海岛主命令.")
    @ConfigEntry(path = "acid.command.island")
    private String playerCommandAliases = "ai";

    @ConfigComment("海岛管理员指令.")
    @ConfigEntry(path = "acid.command.admin")
    private String adminCommandAliases = "acid";
    
    @ConfigComment("新玩家执行主命令时的操作.")
    @ConfigComment("默认为 'create'.")
    @ConfigEntry(path = "acid.command.new-player-action", since = "1.13.1")
    private String defaultNewPlayerAction = "create";

    @ConfigComment("玩家执行主命令时的操作.")
    @ConfigComment("默认为 'go'.")
    @ConfigEntry(path = "acid.command.default-action", since = "1.13.1")
    private String defaultPlayerAction = "go";

    /*      ACID        */
    @ConfigComment("酸海是否可以腐蚀管理员")
    @ConfigEntry(path = "acid.damage-op")
    private boolean acidDamageOp = false;

    @ConfigComment("酸水是否可以腐蚀鸡 - 推荐关闭，鸡比较喜欢游泳")
    @ConfigEntry(path = "acid.damage-chickens")
    private boolean acidDamageChickens = false;

    // Damage
    @ConfigComment("玩家在酸海中受到的伤害. 10 为生命值的一半. 设为 5 可以使游戏简单一些.")
    @ConfigEntry(path = "acid.damage.acid.player")
    private int acidDamage = 10;

    @ConfigComment("酸海对怪物的伤害值. 有盔甲的怪物受到的伤害会减少.")
    @ConfigEntry(path = "acid.damage.acid.monster")
    private int acidDamageMonster = 5;

    @ConfigComment("动物在酸海中受到的伤害值")
    @ConfigEntry(path = "acid.damage.acid.animal")
    private int acidDamageAnimal = 1;

    @ConfigComment("酸海中的物品在几秒后被腐蚀. 0 = 不腐蚀物品")
    @ConfigEntry(path = "acid.damage.acid.item")
    private long acidDestroyItemTime = 0;

    @ConfigComment("酸雨是否造成伤害.")
    @ConfigEntry(path = "acid.damage.rain")
    private int acidRainDamage = 1;

    @ConfigComment("酸雪是否造成伤害")
    @ConfigEntry(path = "acid.damage.snow")
    private boolean acidDamageSnow;

    @ConfigComment("开始受到伤害的延迟")
    @ConfigEntry(path = "acid.damage.delay")
    private long acidDamageDelay = 2;

    @ConfigComment("进入酸海后的药水效果.")
    @ConfigComment("可设置多个.")
    @ConfigComment("可用药水效果:")
    @ConfigComment("   BLINDNESS")
    @ConfigComment("   CONFUSION")
    @ConfigComment("   HUNGER")
    @ConfigComment("   POISON")
    @ConfigComment("   SLOW")
    @ConfigComment("   SLOW_DIGGING")
    @ConfigComment("   WEAKNESS")
    @ConfigEntry(path = "acid.damage.effects")
    @Adapter(PotionEffectListAdapter.class)
    private List<PotionEffectType> acidEffects = new ArrayList<>();
    @ConfigComment("药水效果持续时间")
    @ConfigEntry(path = "acid.damage.acid-effect-duration", since = "1.11.2")
    private int acidEffectDuation = 30;

    @ConfigComment("酸雨/雪附加的药水效果.")
    @ConfigComment("可设多个.")
    @ConfigComment("可用药水效果:")
    @ConfigComment("   BLINDNESS")
    @ConfigComment("   CONFUSION")
    @ConfigComment("   HUNGER")
    @ConfigComment("   POISON")
    @ConfigComment("   SLOW")
    @ConfigComment("   SLOW_DIGGING")
    @ConfigComment("   WEAKNESS")
    @ConfigEntry(path = "acid.damage.rain-effects", since = "1.9.1")
    @Adapter(PotionEffectListAdapter.class)
    private List<PotionEffectType> acidRainEffects = new ArrayList<>();

    @ConfigComment("酸雨/雪药水效果持续时间")
    @ConfigEntry(path = "acid.damage.rain-effect-duration", since = "1.11.2")
    private int rainEffectDuation = 10;

    @ConfigComment("玩家戴头盔后是否还受酸雨/雪影响，false 为受影响")
    @ConfigEntry(path = "acid.damage.protection.helmet")
    private boolean helmetProtection;

    @ConfigComment("玩家穿整套盔甲将不会受到酸海影响，false 为受影响")
    @ConfigEntry(path = "acid.damage.protection.full-armor")
    private boolean fullArmorProtection;


    /*      WORLD       */
    @ConfigComment("酸海世界昵称. 可在管理员指令中使用. 仅输入一个单词")
    @ConfigEntry(path = "world.friendly-name", needsReset = true)
    private String friendlyName = "AcidIsland";

    @ConfigComment("酸岛世界的文件夹名称 - 不存在将会生成.")
    @ConfigComment("对于末地和下界，此选项作为前缀 (比如 acidisland_world, acidisland_world_nether, acidisland_world_end)")
    @ConfigEntry(path = "world.world-name", needsReset = true)
    private String worldName = "acidisland_world";

    @ConfigComment("世界难度设置 - PEACEFUL, EASY, NORMAL, HARD")
    @ConfigComment("其它插件可能覆盖此选项")
    @ConfigEntry(path = "world.difficulty")
    private Difficulty difficulty;

    @ConfigComment("生成限制. 此设置将覆盖 bukkit.yml 中的设置")
    @ConfigComment("设为负数使用服务器默认设置")
    @ConfigEntry(path = "world.spawn-limits.monsters", since = "1.11.2")
    private int spawnLimitMonsters = -1;
    @ConfigEntry(path = "world.spawn-limits.animals", since = "1.11.2")
    private int spawnLimitAnimals = -1;
    @ConfigEntry(path = "world.spawn-limits.water-animals", since = "1.11.2")
    private int spawnLimitWaterAnimals = -1;
    @ConfigEntry(path = "world.spawn-limits.ambient", since = "1.11.2")
    private int spawnLimitAmbient = -1;
    @ConfigComment("设为 0 将会禁止动物生成, 但不推荐. 默认为 400.")
    @ConfigComment("设为负数使用服务器默认设置")
    @ConfigEntry(path = "world.spawn-limits.ticks-per-animal-spawns", since = "1.11.2")
    private int ticksPerAnimalSpawns = -1;
    @ConfigComment("设为 0 禁用怪物生成, 但不推荐. 默认为 400.")
    @ConfigComment("设为负数以使用服务器默认设置")
    @ConfigEntry(path = "world.spawn-limits.ticks-per-monster-spawns", since = "1.11.2")
    private int ticksPerMonsterSpawns = -1;

    @ConfigComment("岛屿半径. (岛屿间距是此值的 2 倍)")
    @ConfigComment("请设为 16 的倍数")
    @ConfigComment("这对每个维度都有效: 主世界, 下界和末地.")
    @ConfigComment("请不要中途改变此选项.")
    @ConfigEntry(path = "world.distance-between-islands", needsReset = true)
    private int islandDistance = 192;

    @ConfigComment("默认保护范围. 不可以比上面的选项大.")
    @ConfigComment("管理员可以使用 /acid range set <玩家> <范围> 单独为玩家设置保护范围")
    @ConfigComment("或赋予权限: acidisland.island.range.<范围值>")
    @ConfigEntry(path = "world.protection-range", overrideOnChange = true)
    private int islandProtectionRange = 100;

    @ConfigComment("首个岛屿的位置. 这是该世界的第一个岛屿所在的位置")
    @ConfigComment("此值必须是岛屿半径(间距)的因数, 如果你算错了, 插件会自动为你纠正")
    @ConfigComment("所有岛屿都会围绕此坐标生成")
    @ConfigComment("因数: a × b = c, 则 a 和 b 为 c 的因数")
    @ConfigComment("如果你看不懂这些, 请不要修改此选项")
    @ConfigEntry(path = "world.start-x", needsReset = true)
    private int islandStartX = 0;

    @ConfigEntry(path = "world.start-z", needsReset = true)
    private int islandStartZ = 0;

    @ConfigEntry(path = "world.offset-x")
    private int islandXOffset;
    @ConfigEntry(path = "world.offset-z")
    private int islandZOffset;

    @ConfigComment("岛屿海拔 - 最低为 5.")
    @ConfigComment("这将是岛屿模板中的基岩所在的 y 坐标.")
    @ConfigEntry(path = "world.island-height")
    private int islandHeight = 50;

    @ConfigComment("使用其它世界生成器.")
    @ConfigComment("如果启用, 本扩展将不会接管世界的生成.")
    @ConfigEntry(path = "world.use-own-generator", experimental = true)
    private boolean useOwnGenerator;

    @ConfigComment("酸海高度 (不要中途改变此选项除非删除世界)")
    @ConfigComment("最低为 0, 将不会有酸海!")
    @ConfigEntry(path = "world.sea-height")
    private int seaHeight = 54;

    @ConfigComment("岛屿数量限制. 设为 -1 或 0 禁用.")
    @ConfigComment("若岛屿数量 ≥ 此选项, 新玩家将无法再创建岛屿.")
    @ConfigEntry(path = "world.max-islands")
    private int maxIslands = -1;

    @ConfigComment("默认游戏模式. 比如玩家创建岛屿时就会被设为此游戏模式")
    @ConfigComment("可用项: SURVIVAL(生存模式), CREATIVE(创造模式), ADVENTURE(冒险模式), SPECTATOR(旁观模式)")
    @ConfigEntry(path = "world.default-game-mode")
    private GameMode defaultGameMode = GameMode.SURVIVAL;

    @ConfigComment("默认生物群系(仅主世界有效)")
    @ConfigEntry(path = "world.default-biome")
    private Biome defaultBiome = Biome.WARM_OCEAN;

    @ConfigComment("玩家默认一次最多能封禁多少个玩家.")
    @ConfigComment("权限为 acidisland.ban.maxlimit.X")
    @ConfigComment("-1 = 无限")
    @ConfigEntry(path = "world.ban-limit")
    private int banLimit = -1;

    // Nether
    @ConfigComment("是否生成下界 - 若设为 false, 下界将不会生成且下界传送门将无效. 某些插件可能仍会启用下界传送门.")
    @ConfigComment("注意: 某些默认挑战需要下界才能完成.")
    @ConfigComment("不管玩家从何处进入下界")
    @ConfigComment("在下界进入传送门时都会传送到自己的岛屿上.")
    @ConfigEntry(path = "world.nether.generate")
    private boolean netherGenerate = true;

    @ConfigComment("是否生成岛屿. 设为 false 将生成原版下界.")
    @ConfigEntry(path = "world.nether.islands", needsReset = true)
    private boolean netherIslands = true;

    @ConfigComment("下界的酸海高度，仅上方设置为 true 时有效")
    @ConfigComment("中途改变此设置将会引发问题!")
    @ConfigEntry(path = "world.nether.sea-height", needsReset = true)
    private int netherSeaHeight = 54;

    @ConfigComment("是否生成下界顶层, 若为 false, 下界顶层将不会生成")
    @ConfigComment("若生成卡顿, 可以考虑关闭此功能")
    @ConfigComment("仅对下界生效")
    @ConfigEntry(path = "world.nether.roof")
    private boolean netherRoof = true;

    @ConfigComment("下界出生点保护")
    @ConfigComment("在此范围内的玩家无法进行任何世界交互")
    @ConfigComment("最小为 0 (不推荐), 最大为 100. 默认为 25.")
    @ConfigComment("仅对原版下界有效, 即上方 islands 设为 false")
    @ConfigEntry(path = "world.nether.spawn-radius")
    private int netherSpawnRadius = 32;

    // End
    @ConfigComment("是否启用末地")
    @ConfigEntry(path = "world.end.generate")
    private boolean endGenerate = true;

    @ConfigComment("是否生成岛屿，设为 false 将生成原版末地")
    @ConfigEntry(path = "world.end.islands", needsReset = true)
    private boolean endIslands = true;

    @ConfigComment("末地的酸海高度，仅上方设置为 true 时有效.")
    @ConfigComment("中途改变此设置将引发问题!")
    @ConfigEntry(path = "world.end.sea-height", needsReset = true)
    private int endSeaHeight = 54;

    @ConfigEntry(path = "world.end.dragon-spawn", experimental = true)
    private boolean dragonSpawn = false;

    @ConfigComment("敌对生物允许名单 - 这些生物不会在执行 /island 命令或登录时清除")
    @ConfigEntry(path = "world.remove-mobs-whitelist")
    private Set<EntityType> removeMobsWhitelist = new HashSet<>();

    @ConfigComment("世界设置.")
    @ConfigEntry(path = "world.flags")
    private Map<String, Boolean> worldFlags = new HashMap<>();

    @ConfigComment("这是新岛屿的默认保护策略.")
    @ConfigComment("只有等级大于等于所设值时才能执行该行为")
    @ConfigComment("等级列表: 游客 = 0, 成员 = 900, 岛主 = 1000")
    @ConfigEntry(path = "world.default-island-flags")
    @Adapter(FlagSerializer.class)
    private Map<Flag, Integer> defaultIslandFlags = new HashMap<>();

    @ConfigComment("新岛屿的默认设置")
    @ConfigEntry(path = "world.default-island-settings")
    @Adapter(FlagSerializer2.class)
    private Map<Flag, Integer> defaultIslandSettings = new HashMap<>();

    @ConfigComment("这些设置将对玩家隐藏")
    @ConfigComment("当然你也可以在游戏中设置隐藏/显示(见 Wiki)")
    @ConfigEntry(path = "world.hidden-flags")
    private List<String> hiddenFlags = new ArrayList<>();

    @ConfigComment("游客不可以在岛屿内使用这些指令")
    @ConfigEntry(path = "world.visitor-banned-commands")
    private List<String> visitorBannedCommands = new ArrayList<>();

    @ConfigComment("玩家不可以在掉落时使用这些指令")
    @ConfigComment("仅在 PREVENT_TELEPORT_WHEN_FALLING 设置开启时有效")
    @ConfigEntry(path = "world.falling-banned-commands")
    private List<String> fallingBannedCommands = new ArrayList<>();

    // ---------------------------------------------

    /*      ISLAND      */
    @ConfigComment("队伍的最大人数")
    @ConfigComment("用权限设置的人数不可低于此值.")
    @ConfigComment("权限为: acidisland.team.maxsize.<人数>")
    @ConfigEntry(path = "island.max-team-size")
    private int maxTeamSize = 4;

    @ConfigComment("每个岛屿的合作者的最大人数")
    @ConfigComment("赋予玩家 acidisland.coop.maxsize.<人数> 权限可覆盖此选项")
    @ConfigComment("权限所设人数不得小于此值")
    @ConfigEntry(path = "island.max-coop-size", since = "1.13.0")
    private int maxCoopSize = 4;

    @ConfigComment("每个岛屿最多可以有多少个受信任玩家")
    @ConfigComment("赋予玩家 acidisland.trust.maxsize.<人数> 权限可覆盖此选项")
    @ConfigComment("权限所设人数不得小于此值")
    @ConfigEntry(path = "island.max-trusted-size", since = "1.13.0")
    private int maxTrustSize = 4;

    @ConfigComment("每个玩家最多可以有几个家. 最少 = 1")
    @ConfigComment("多于 1 个家的指令格式: /ai sethome <第几个家> 或 /ai go <第几个家>")
    @ConfigComment("权限: acidisland.island.maxhomes.<数量>")
    @ConfigEntry(path = "island.max-homes")
    private int maxHomes = 5;

    // Reset
    @ConfigComment("玩家最多可以重置多少次岛屿 (可使用 /acid reset add/remove/reset/set 指令设置)")
    @ConfigComment("-1 意为无限, 0 为禁止重置.")
    @ConfigComment("比如, 2 次重置表示玩家有 2 次重置机会或 3 次获得新岛屿的机会")
    @ConfigEntry(path = "island.reset.reset-limit")
    private int resetLimit = -1;

    @ConfigComment("被踢出或主动离开队伍的玩家将会失去一次岛屿重置机会")
    @ConfigComment("如果玩家的重置机会为 0, 且离开了队伍, 该玩家将不能再创建新岛屿, 只能加入其它队伍")
    @ConfigComment("设为 true 可以避免玩家用不正常的方式使用队伍功能")
    @ConfigEntry(path = "island.reset.leavers-lose-reset")
    private boolean leaversLoseReset = false;

    @ConfigComment("保留被踢出玩家的背包物品.")
    @ConfigComment("此项将覆盖 on-leave.inventory 设置.")
    @ConfigEntry(path = "island.reset.kicked-keep-inventory")
    private boolean kickedKeepInventory = false;

    @ConfigComment("当玩家创建或加入一个岛屿时扩展是否重置这些项目")
    @ConfigComment("重置金钱 - 若为 true, 将会将玩家的金钱重置为默认值")
    @ConfigComment("推荐开启此功能, 但是如果你的服务器上还有其他玩法且你还想开启此功能")
    @ConfigComment("请使用支持不同世界不同金钱的插件.")
    @ConfigEntry(path = "island.reset.on-join.money")
    private boolean onJoinResetMoney = false;

    @ConfigComment("重置背包 - 若为 true, 玩家的背包将会被清空.")
    @ConfigComment("注意: 如果你安装了 MultiInv 或其他相似的背包管理插件, 那些插件仍会在")
    @ConfigComment("切换世界时更换玩家的背包.")
    @ConfigEntry(path = "island.reset.on-join.inventory")
    private boolean onJoinResetInventory = false;

    @ConfigComment("重置血量 - 若为 true, 玩家将被治愈.")
    @ConfigEntry(path = "island.reset.on-join.health")
    private boolean onJoinResetHealth = true;

    @ConfigComment("重置饥饿值 - 若为 true, 玩家将被喂饱.")
    @ConfigEntry(path = "island.reset.on-join.hunger")
    private boolean onJoinResetHunger = true;

    @ConfigComment("重置经验值 - 若为 true, 玩家的经验值将被重置.")
    @ConfigEntry(path = "island.reset.on-join.exp")
    private boolean onJoinResetXP = false;

    @ConfigComment("重置末影箱 - 若为 true, 玩家末影箱中的物品将被清空.")
    @ConfigEntry(path = "island.reset.on-join.ender-chest")
    private boolean onJoinResetEnderChest = false;

    @ConfigComment("当玩家离开/被踢出一个岛屿时,扩展是否重置以下项目")
    @ConfigComment("重置金钱 - 若为 true, 将会将玩家的金钱重置为默认值")
    @ConfigComment("推荐开启此功能, 但是如果你的服务器上还有其他玩法且你还想开启此功能")
    @ConfigComment("请使用支持不同世界不同金钱的插件.")
    @ConfigEntry(path = "island.reset.on-leave.money")
    private boolean onLeaveResetMoney = false;

    @ConfigComment("重置背包 - 若为 true, 玩家的背包将会被清空.")
    @ConfigComment("注意: 如果你安装了 MultiInv 或其他相似的背包管理插件, 那些插件仍会在")
    @ConfigComment("切换世界时更换玩家的背包.")
    @ConfigEntry(path = "island.reset.on-leave.inventory")
    private boolean onLeaveResetInventory = false;

    @ConfigComment("重置血量 - 若为 true, 玩家将被治愈.")
    @ConfigEntry(path = "island.reset.on-leave.health")
    private boolean onLeaveResetHealth = false;

    @ConfigComment("重置饥饿值 - 若为 true, 玩家将被喂饱.")
    @ConfigEntry(path = "island.reset.on-leave.hunger")
    private boolean onLeaveResetHunger = false;

    @ConfigComment("重置经验值 - 若为 true, 玩家的经验值将被重置.")
    @ConfigEntry(path = "island.reset.on-leave.exp")
    private boolean onLeaveResetXP = false;

    @ConfigComment("重置末影箱 - 若为 true, 玩家末影箱中的物品将被清空.")
    @ConfigEntry(path = "island.reset.on-leave.ender-chest")
    private boolean onLeaveResetEnderChest = false;

    @ConfigComment("是否在玩家第一次进入服务器时自动创建该玩家的岛屿.")
    @ConfigComment("若设为 true,")
    @ConfigComment("   * 当玩家第一次加入服务器时, 该玩家将被告知")
    @ConfigComment("    岛屿已创建.")
    @ConfigComment("  * 请确保你有一个叫 \"default\" 的模板: 此功能将用")
    @ConfigComment("    此模板创建岛屿.")
    @ConfigComment("  * 玩家不必执行指令即可创建岛屿.")
    @ConfigComment("若设为 false, 此功能将被完全禁用.")
    @ConfigComment("警告:")
    @ConfigComment("  * 如果你的服务器(单端)运行了多个游戏模式, 并且都")
    @ConfigComment("    开启了此功能, 所有游戏模式的岛屿都会被自动创建.")
    @ConfigComment("    但是, 玩家之后将被传送到哪个游戏模式是随机的.")
    @ConfigComment("  * 创建岛屿是比较占用服务器性能的, 请认真考虑是否启用此功能")
    @ConfigComment("来相对的避免潜在的问题发生, 尤其是服务器人流量较大时.")
    @ConfigEntry(path = "island.create-island-on-first-login.enable")
    private boolean createIslandOnFirstLoginEnabled;

    @ConfigComment("玩家登录服务器多少秒后, 岛屿将开始创建.")
    @ConfigComment("若所设值 ≤ 0, 岛屿将在玩家登录时立即创建.")
    @ConfigComment("建议此值不要大于 1 分钟(60 秒).")
    @ConfigEntry(path = "island.create-island-on-first-login.delay")
    private int createIslandOnFirstLoginDelay = 5;

    @ConfigComment("若玩家在上方所设的时间内离开了服务器")
    @ConfigComment("是否停止岛屿的创建.")
    @ConfigComment("若设为 true,")
    @ConfigComment("  * 若玩家在延缓期内离开了服务器，岛屿创建会停止.")
    @ConfigComment("    * 若玩家之后又加入了服务器，ta 不会再被视为新玩家，因此岛屿不会自动创建.")
    @ConfigComment("  * 若玩家在岛屿创建阶段离开了服务器, 岛屿会继续创建.")
    @ConfigComment("若设为 false, 无论如何岛屿都会在延迟过后创建.")
    @ConfigComment("若上方选项小于等于 0 ，此设置无任何效果.")
    @ConfigEntry(path = "island.create-island-on-first-login.abort-on-logout")
    private boolean createIslandOnFirstLoginAbortOnLogout = true;

    @ConfigComment("当玩家进入传送门时，如果末地/下界岛屿不存在是否生成.")
    @ConfigComment("一般末地和下界岛屿都会在玩家创建岛屿时自动生成，但是")
    @ConfigComment("由于某些神奇的原因导致它们没有生成时，可以开启此选项.")
    @ConfigComment("Note that bedrock removal glitches can exploit this option.")
    @ConfigEntry(path = "island.create-missing-nether-end-islands")
    private boolean pasteMissingIslands = false;

    // Commands
    @ConfigComment("当玩家加入或创建岛屿时执行的指令.")
    @ConfigEntry(path = "island.commands.on-join")
    private List<String> onJoinCommands = new ArrayList<>();

    @ConfigComment("当玩家离开岛屿、重置岛屿或被踢出岛屿时执行的指令.")
    @ConfigEntry(path = "island.commands.on-leave")
    private List<String> onLeaveCommands = new ArrayList<>();

    // Sethome
    @ConfigEntry(path = "island.sethome.nether.allow")
    private boolean allowSetHomeInNether = true;

    @ConfigEntry(path = "island.sethome.nether.require-confirmation")
    private boolean requireConfirmationToSetHomeInNether = true;

    @ConfigEntry(path = "island.sethome.the-end.allow")
    private boolean allowSetHomeInTheEnd = true;

    @ConfigEntry(path = "island.sethome.the-end.require-confirmation")
    private boolean requireConfirmationToSetHomeInTheEnd = true;

    // Deaths
    @ConfigComment("是否记录死亡次数.")
    @ConfigEntry(path = "island.deaths.counted")
    private boolean deathsCounted = true;

    @ConfigComment("最多记录的死亡次数. 死亡数可能会被其它扩展使用.")
    @ConfigEntry(path = "island.deaths.max")
    private int deathsMax = 10;

    @ConfigComment("当玩家加入一个队伍时，重置 ta 的死亡数")
    @ConfigEntry(path = "island.deaths.team-join-reset")
    private boolean teamJoinDeathReset = true;

    @ConfigComment("是否在玩家创建新岛/重置岛屿时重置死亡数")
    @ConfigEntry(path = "island.deaths.reset-on-new-island")
    private boolean deathsResetOnNewIsland = true;

    // Ranks
    @ConfigEntry(path = "island.customranks")
    private Map<String, Integer> customRanks = new HashMap<>();

    // ---------------------------------------------

    /*      PROTECTION      */
    @ConfigComment("区域限制型生物.")
    @ConfigComment("离开出生岛的这些生物将被移除.")
    @ConfigEntry(path = "protection.geo-limit-settings")
    private List<String> geoLimitSettings = new ArrayList<>();

    // Invincible visitor settings
    @ConfigComment("无敌游客设置. 这些伤害不会影响游客.")
    @ConfigComment("留空以关闭无敌游客模式")
    @ConfigEntry(path = "protection.invincible-visitors")
    private List<String> ivSettings = new ArrayList<>();

    //---------------------------------------------------------------------------------------/
    @ConfigComment("请勿修改此处")
    @ConfigEntry(path = "do-not-edit-these-settings.reset-epoch")
    private long resetEpoch = 0;


    /**
     * @return the acidDamage
     */
    public int getAcidDamage() {
        return acidDamage;
    }
    /**
     * @return the acidDamageAnimal
     */
    public int getAcidDamageAnimal() {
        return acidDamageAnimal;
    }
    /**
     * @return the acidDamageDelay
     */
    public long getAcidDamageDelay() {
        return acidDamageDelay;
    }
    /**
     * @return the acidDamageMonster
     */
    public int getAcidDamageMonster() {
        return acidDamageMonster;
    }
    /**
     * @return the acidDestroyItemTime
     */
    public long getAcidDestroyItemTime() {
        return acidDestroyItemTime;
    }
    /**
     * @return the acidEffects
     */
    public List<PotionEffectType> getAcidEffects() {
        return acidEffects;
    }
    /**
     * @return the acidRainDamage
     */
    public int getAcidRainDamage() {
        return acidRainDamage;
    }

    @Override
    public int getBanLimit() {
        return banLimit;
    }
    //---------------------------------------------------------------------------------------/
    /**
     * @return the customRanks
     */
    public Map<String, Integer> getCustomRanks() {
        return customRanks;
    }
    /**
     * @return the deathsMax
     */
    @Override
    public int getDeathsMax() {
        return deathsMax;
    }
    /**
     * @return the defaultBiome
     */
    public Biome getDefaultBiome() {
        return defaultBiome;
    }
    /**
     * @return the defaultGameMode
     */
    @Override
    public GameMode getDefaultGameMode() {
        return defaultGameMode;
    }
    /**
     * @return the defaultIslandProtection
     */
    @Override
    public Map<Flag, Integer> getDefaultIslandFlags() {
        return defaultIslandFlags;
    }
    /**
     * @return the defaultIslandSettings
     */
    @Override
    public Map<Flag, Integer> getDefaultIslandSettings() {
        return defaultIslandSettings;
    }
    /**
     * @return the difficulty
     */
    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }
    /**
     * @return the endSeaHeight
     */
    public int getEndSeaHeight() {
        return endSeaHeight;
    }
    /* (non-Javadoc)
     * @see world.bentobox.bbox.api.configuration.WorldSettings#getFriendlyName()
     */
    @Override
    public String getFriendlyName() {
        return friendlyName;
    }
    /**
     * @return the geoLimitSettings
     */
    @Override
    public List<String> getGeoLimitSettings() {
        return geoLimitSettings;
    }
    /**
     * @return the islandDistance
     */
    @Override
    public int getIslandDistance() {
        return islandDistance;
    }
    /**
     * @return the islandHeight
     */
    @Override
    public int getIslandHeight() {
        return islandHeight;
    }
    /**
     * @return the islandProtectionRange
     */
    @Override
    public int getIslandProtectionRange() {
        return islandProtectionRange;
    }
    /**
     * @return the islandStartX
     */
    @Override
    public int getIslandStartX() {
        return islandStartX;
    }
    /**
     * @return the islandStartZ
     */
    @Override
    public int getIslandStartZ() {
        return islandStartZ;
    }
    /**
     * @return the islandXOffset
     */
    @Override
    public int getIslandXOffset() {
        return islandXOffset;
    }
    /**
     * @return the islandZOffset
     */
    @Override
    public int getIslandZOffset() {
        return islandZOffset;
    }
    /**
     * Invincible visitor settings
     * @return the ivSettings
     */
    @Override
    public List<String> getIvSettings() {
        return ivSettings;
    }
    /**
     * @return the maxHomes
     */
    @Override
    public int getMaxHomes() {
        return maxHomes;
    }
    /**
     * @return the maxIslands
     */
    @Override
    public int getMaxIslands() {
        return maxIslands;
    }
    /**
     * @return the maxTeamSize
     */
    @Override
    public int getMaxTeamSize() {
        return maxTeamSize;
    }
    /**
     * @return the netherSeaHeight
     */
    public int getNetherSeaHeight() {
        return netherSeaHeight;
    }
    /**
     * @return the netherSpawnRadius
     */
    @Override
    public int getNetherSpawnRadius() {
        return netherSpawnRadius;
    }
    @Override
    public String getPermissionPrefix() {
        return "acidisland";
    }
    /**
     * @return the removeMobsWhitelist
     */
    @Override
    public Set<EntityType> getRemoveMobsWhitelist() {
        return removeMobsWhitelist;
    }
    @Override
    public long getResetEpoch() {
        return resetEpoch;
    }
    /**
     * @return the resetLimit
     */
    @Override
    public int getResetLimit() {
        return resetLimit;
    }
    /**
     * @return the seaHeight
     */
    @Override
    public int getSeaHeight() {
        return seaHeight;
    }
    /**
     * @return the hidden flags
     */
    @Override
    public List<String> getHiddenFlags() {
        return hiddenFlags;
    }
    /**
     * @return the visitorbannedcommands
     */
    @Override
    public List<String> getVisitorBannedCommands() {
        return visitorBannedCommands;
    }

    /**
     * @return the fallingBannedCommands
     */
    @Override
    public List<String> getFallingBannedCommands() {
        return fallingBannedCommands;
    }

    /**
     * @return the worldFlags
     */
    @Override
    public Map<String, Boolean> getWorldFlags() {
        return worldFlags;
    }
    /**
     * @return the worldName
     */
    @Override
    public String getWorldName() {
        return worldName;
    }
    /**
     * @return the acidDamageChickens
     */
    public boolean isAcidDamageChickens() {
        return acidDamageChickens;
    }
    /**
     * @return the acidDamageOp
     */
    public boolean isAcidDamageOp() {
        return acidDamageOp;
    }
    /**
     * @return the allowSetHomeInNether
     */
    @Override
    public boolean isAllowSetHomeInNether() {
        return allowSetHomeInNether;
    }
    /**
     * @return the allowSetHomeInTheEnd
     */
    @Override
    public boolean isAllowSetHomeInTheEnd() {
        return allowSetHomeInTheEnd;
    }
    /**
     * @return the isDeathsCounted
     */
    @Override
    public boolean isDeathsCounted() {
        return deathsCounted;
    }
    /**
     * @return the dragonSpawn
     */
    @Override
    public boolean isDragonSpawn() {
        return dragonSpawn;
    }
    /**
     * @return the endGenerate
     */
    @Override
    public boolean isEndGenerate() {
        return endGenerate;
    }
    /**
     */
    @Override
    public boolean isEndIslands() {
        return endIslands;
    }
    /**
     * @return the fullArmorProtection
     */
    public boolean isFullArmorProtection() {
        return fullArmorProtection;
    }
    /**
     * @return the helmetProtection
     */
    public boolean isHelmetProtection() {
        return helmetProtection;
    }
    /**
     * @return the kickedKeepInventory
     */
    @Override
    public boolean isKickedKeepInventory() {
        return kickedKeepInventory;
    }


    /**
     * This method returns the createIslandOnFirstLoginEnabled boolean value.
     * @return the createIslandOnFirstLoginEnabled value
     * @since 1.9.0
     */
    @Override
    public boolean isCreateIslandOnFirstLoginEnabled()
    {
        return createIslandOnFirstLoginEnabled;
    }


    /**
     * This method returns the createIslandOnFirstLoginDelay int value.
     * @return the createIslandOnFirstLoginDelay value
     * @since 1.9.0
     */
    @Override
    public int getCreateIslandOnFirstLoginDelay()
    {
        return createIslandOnFirstLoginDelay;
    }


    /**
     * This method returns the createIslandOnFirstLoginAbortOnLogout boolean value.
     * @return the createIslandOnFirstLoginAbortOnLogout value
     * @since 1.9.0
     */
    @Override
    public boolean isCreateIslandOnFirstLoginAbortOnLogout()
    {
        return createIslandOnFirstLoginAbortOnLogout;
    }


    /**
     * @return the leaversLoseReset
     */
    @Override
    public boolean isLeaversLoseReset() {
        return leaversLoseReset;
    }
    /**
     * @return the netherGenerate
     */
    @Override
    public boolean isNetherGenerate() {
        return netherGenerate;
    }
    /**
     * @return the netherIslands
     */
    @Override
    public boolean isNetherIslands() {
        return netherIslands;
    }
    /**
     * @return the netherRoof
     */
    public boolean isNetherRoof() {
        return netherRoof;
    }
    /**
     * @return the onJoinResetEnderChest
     */
    @Override
    public boolean isOnJoinResetEnderChest() {
        return onJoinResetEnderChest;
    }
    /**
     * @return the onJoinResetInventory
     */
    @Override
    public boolean isOnJoinResetInventory() {
        return onJoinResetInventory;
    }
    /**
     * @return the onJoinResetMoney
     */
    @Override
    public boolean isOnJoinResetMoney() {
        return onJoinResetMoney;
    }
    /**
     * @return the onLeaveResetEnderChest
     */
    @Override
    public boolean isOnLeaveResetEnderChest() {
        return onLeaveResetEnderChest;
    }
    /**
     * @return the onLeaveResetInventory
     */
    @Override
    public boolean isOnLeaveResetInventory() {
        return onLeaveResetInventory;
    }
    /**
     * @return the onLeaveResetMoney
     */
    @Override
    public boolean isOnLeaveResetMoney() {
        return onLeaveResetMoney;
    }
    /**
     * @return the requireConfirmationToSetHomeInNether
     */
    @Override
    public boolean isRequireConfirmationToSetHomeInNether() {
        return requireConfirmationToSetHomeInNether;
    }
    /**
     * @return the requireConfirmationToSetHomeInTheEnd
     */
    @Override
    public boolean isRequireConfirmationToSetHomeInTheEnd() {
        return requireConfirmationToSetHomeInTheEnd;
    }
    @Override
    public boolean isTeamJoinDeathReset() {
        return teamJoinDeathReset;
    }
    /**
     * @return the useOwnGenerator
     */
    @Override
    public boolean isUseOwnGenerator() {
        return useOwnGenerator;
    }
    @Override
    public boolean isWaterUnsafe() {
        // Water is unsafe in acid island
        return true;
    }
    /**
     * @param acidDamage the acidDamage to set
     */
    public void setAcidDamage(int acidDamage) {
        this.acidDamage = acidDamage;
    }
    /**
     * @param acidDamageAnimal the acidDamageAnimal to set
     */
    public void setAcidDamageAnimal(int acidDamageAnimal) {
        this.acidDamageAnimal = acidDamageAnimal;
    }
    /**
     * @param acidDamageChickens the acidDamageChickens to set
     */
    public void setAcidDamageChickens(boolean acidDamageChickens) {
        this.acidDamageChickens = acidDamageChickens;
    }
    /**
     * @param acidDamageDelay the acidDamageDelay to set
     */
    public void setAcidDamageDelay(long acidDamageDelay) {
        this.acidDamageDelay = acidDamageDelay;
    }
    /**
     * @param acidDamageMonster the acidDamageMonster to set
     */
    public void setAcidDamageMonster(int acidDamageMonster) {
        this.acidDamageMonster = acidDamageMonster;
    }
    /**
     * @param acidDamageOp the acidDamageOp to set
     */
    public void setAcidDamageOp(boolean acidDamageOp) {
        this.acidDamageOp = acidDamageOp;
    }
    /**
     * @param acidDestroyItemTime the acidDestroyItemTime to set
     */
    public void setAcidDestroyItemTime(long acidDestroyItemTime) {
        this.acidDestroyItemTime = acidDestroyItemTime;
    }
    /**
     * @param acidEffects the acidEffects to set
     */
    public void setAcidEffects(List<PotionEffectType> acidEffects) {
        this.acidEffects = acidEffects;
    }
    /**
     * @param acidRainDamage the acidRainDamage to set
     */
    public void setAcidRainDamage(int acidRainDamage) {
        this.acidRainDamage = acidRainDamage;
    }
    /**
     * @param adminCommand what you want your admin command to be
     */
    public void setAdminCommand(String adminCommand) { this.adminCommandAliases = adminCommand; }
    /**
     * @param allowSetHomeInNether the allowSetHomeInNether to set
     */
    public void setAllowSetHomeInNether(boolean allowSetHomeInNether) {
        this.allowSetHomeInNether = allowSetHomeInNether;
    }
    /**
     * @param allowSetHomeInTheEnd the allowSetHomeInTheEnd to set
     */
    public void setAllowSetHomeInTheEnd(boolean allowSetHomeInTheEnd) {
        this.allowSetHomeInTheEnd = allowSetHomeInTheEnd;
    }
    /**
     * @param banLimit the banLimit to set
     */
    public void setBanLimit(int banLimit) {
        this.banLimit = banLimit;
    }
    /**
     * @param customRanks the customRanks to set
     */
    public void setCustomRanks(Map<String, Integer> customRanks) {
        this.customRanks = customRanks;
    }
    /**
     * @param deathsCounted the deathsCounted to set
     */
    public void setDeathsCounted(boolean deathsCounted) {
        this.deathsCounted = deathsCounted;
    }
    /**
     * @param deathsMax the deathsMax to set
     */
    public void setDeathsMax(int deathsMax) {
        this.deathsMax = deathsMax;
    }
    /**
     * @param defaultBiome the defaultBiome to set
     */
    public void setDefaultBiome(Biome defaultBiome) {
        this.defaultBiome = defaultBiome;
    }
    /**
     * @param defaultGameMode the defaultGameMode to set
     */
    public void setDefaultGameMode(GameMode defaultGameMode) {
        this.defaultGameMode = defaultGameMode;
    }
    /**
     */
    public void setDefaultIslandFlags(Map<Flag, Integer> defaultIslandFlags) {
        this.defaultIslandFlags = defaultIslandFlags;
    }
    /**
     * @param defaultIslandSettings the defaultIslandSettings to set
     */
    public void setDefaultIslandSettings(Map<Flag, Integer> defaultIslandSettings) {
        this.defaultIslandSettings = defaultIslandSettings;
    }
    /**
     * @param difficulty the difficulty to set
     */
    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    /**
     * @param dragonSpawn the dragonSpawn to set
     */
    public void setDragonSpawn(boolean dragonSpawn) {
        this.dragonSpawn = dragonSpawn;
    }
    /**
     * @param endGenerate the endGenerate to set
     */
    public void setEndGenerate(boolean endGenerate) {
        this.endGenerate = endGenerate;
    }
    /**
     * @param endIslands the endIslands to set
     */
    public void setEndIslands(boolean endIslands) {
        this.endIslands = endIslands;
    }

    /**
     * @param friendlyName the friendlyName to set
     */
    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }
    /**
     * @param fullArmorProtection the fullArmorProtection to set
     */
    public void setFullArmorProtection(boolean fullArmorProtection) {
        this.fullArmorProtection = fullArmorProtection;
    }
    /**
     * @param geoLimitSettings the geoLimitSettings to set
     */
    public void setGeoLimitSettings(List<String> geoLimitSettings) {
        this.geoLimitSettings = geoLimitSettings;
    }

    /**
     * @param helmetProtection the helmetProtection to set
     */
    public void setHelmetProtection(boolean helmetProtection) {
        this.helmetProtection = helmetProtection;
    }
    /**
     * @param islandCommand what you want your island command to be
     */
    public void setIslandCommand(String islandCommand) { this.playerCommandAliases = islandCommand; }

    /**
     * @param islandDistance the islandDistance to set
     */
    public void setIslandDistance(int islandDistance) {
        this.islandDistance = islandDistance;
    }
    /**
     * @param islandHeight the islandHeight to set
     */
    public void setIslandHeight(int islandHeight) {
        this.islandHeight = islandHeight;
    }

    /**
     * @param islandProtectionRange the islandProtectionRange to set
     */
    public void setIslandProtectionRange(int islandProtectionRange) {
        this.islandProtectionRange = islandProtectionRange;
    }

    /**
     * @param islandStartX the islandStartX to set
     */
    public void setIslandStartX(int islandStartX) {
        this.islandStartX = islandStartX;
    }
    /**
     * @param islandStartZ the islandStartZ to set
     */
    public void setIslandStartZ(int islandStartZ) {
        this.islandStartZ = islandStartZ;
    }
    /**
     * @param islandXOffset the islandXOffset to set
     */
    public void setIslandXOffset(int islandXOffset) {
        this.islandXOffset = islandXOffset;
    }
    /**
     * @param islandZOffset the islandZOffset to set
     */
    public void setIslandZOffset(int islandZOffset) {
        this.islandZOffset = islandZOffset;
    }
    /**
     * @param ivSettings the ivSettings to set
     */
    public void setIvSettings(List<String> ivSettings) {
        this.ivSettings = ivSettings;
    }
    /**
     * @param kickedKeepInventory the kickedKeepInventory to set
     */
    public void setKickedKeepInventory(boolean kickedKeepInventory) {
        this.kickedKeepInventory = kickedKeepInventory;
    }
    /**
     * @param leaversLoseReset the leaversLoseReset to set
     */
    public void setLeaversLoseReset(boolean leaversLoseReset) {
        this.leaversLoseReset = leaversLoseReset;
    }
    /**
     * @param maxHomes the maxHomes to set
     */
    public void setMaxHomes(int maxHomes) {
        this.maxHomes = maxHomes;
    }
    /**
     * @param maxIslands the maxIslands to set
     */
    public void setMaxIslands(int maxIslands) {
        this.maxIslands = maxIslands;
    }
    /**
     * @param maxTeamSize the maxTeamSize to set
     */
    public void setMaxTeamSize(int maxTeamSize) {
        this.maxTeamSize = maxTeamSize;
    }
    /**
     * @param netherGenerate the netherGenerate to set
     */
    public void setNetherGenerate(boolean netherGenerate) {
        this.netherGenerate = netherGenerate;
    }
    /**
     * @param netherIslands the netherIslands to set
     */
    public void setNetherIslands(boolean netherIslands) {
        this.netherIslands = netherIslands;
    }
    /**
     * @param netherRoof the netherRoof to set
     */
    public void setNetherRoof(boolean netherRoof) {
        this.netherRoof = netherRoof;
    }
    /**
     * @param netherSpawnRadius the netherSpawnRadius to set
     */
    public void setNetherSpawnRadius(int netherSpawnRadius) {
        this.netherSpawnRadius = netherSpawnRadius;
    }
    /**
     * @param onJoinResetEnderChest the onJoinResetEnderChest to set
     */
    public void setOnJoinResetEnderChest(boolean onJoinResetEnderChest) {
        this.onJoinResetEnderChest = onJoinResetEnderChest;
    }

    /**
     * @param onJoinResetInventory the onJoinResetInventory to set
     */
    public void setOnJoinResetInventory(boolean onJoinResetInventory) {
        this.onJoinResetInventory = onJoinResetInventory;
    }

    /**
     * @param onJoinResetMoney the onJoinResetMoney to set
     */
    public void setOnJoinResetMoney(boolean onJoinResetMoney) {
        this.onJoinResetMoney = onJoinResetMoney;
    }
    /**
     * @param onLeaveResetEnderChest the onLeaveResetEnderChest to set
     */
    public void setOnLeaveResetEnderChest(boolean onLeaveResetEnderChest) {
        this.onLeaveResetEnderChest = onLeaveResetEnderChest;
    }

    /**
     * @param onLeaveResetInventory the onLeaveResetInventory to set
     */
    public void setOnLeaveResetInventory(boolean onLeaveResetInventory) {
        this.onLeaveResetInventory = onLeaveResetInventory;
    }
    /**
     * @param onLeaveResetMoney the onLeaveResetMoney to set
     */
    public void setOnLeaveResetMoney(boolean onLeaveResetMoney) {
        this.onLeaveResetMoney = onLeaveResetMoney;
    }

    /**
     * @param removeMobsWhitelist the removeMobsWhitelist to set
     */
    public void setRemoveMobsWhitelist(Set<EntityType> removeMobsWhitelist) {
        this.removeMobsWhitelist = removeMobsWhitelist;
    }
    /**
     * @param requireConfirmationToSetHomeInNether the requireConfirmationToSetHomeInNether to set
     */
    public void setRequireConfirmationToSetHomeInNether(boolean requireConfirmationToSetHomeInNether) {
        this.requireConfirmationToSetHomeInNether = requireConfirmationToSetHomeInNether;
    }
    /**
     * @param requireConfirmationToSetHomeInTheEnd the requireConfirmationToSetHomeInTheEnd to set
     */
    public void setRequireConfirmationToSetHomeInTheEnd(boolean requireConfirmationToSetHomeInTheEnd) {
        this.requireConfirmationToSetHomeInTheEnd = requireConfirmationToSetHomeInTheEnd;
    }
    @Override
    public void setResetEpoch(long timestamp) {
        this.resetEpoch = timestamp;
    }
    /**
     * @param resetLimit the resetLimit to set
     */
    public void setResetLimit(int resetLimit) {
        this.resetLimit = resetLimit;
    }
    /**
     * @param seaHeight the seaHeight to set
     */
    public void setSeaHeight(int seaHeight) {
        this.seaHeight = seaHeight;
    }
    /**
     * @param netherSeaHeight the netherSeaHeight to set
     */
    public void setNetherSeaHeight(int netherSeaHeight) {
        this.netherSeaHeight = netherSeaHeight;
    }
    /**
     * @param endSeaHeight the endSeaHeight to set
     */
    public void setEndSeaHeight(int endSeaHeight) {
        this.endSeaHeight = endSeaHeight;
    }
    /**
     * @param teamJoinDeathReset the teamJoinDeathReset to set
     */
    public void setTeamJoinDeathReset(boolean teamJoinDeathReset) {
        this.teamJoinDeathReset = teamJoinDeathReset;
    }

    /**
     * @param useOwnGenerator the useOwnGenerator to set
     */
    public void setUseOwnGenerator(boolean useOwnGenerator) {
        this.useOwnGenerator = useOwnGenerator;
    }

    /**
     * @param hiddenFlags the hidden flags to set
     */
    public void setHiddenFlags(List<String> hiddenFlags) {
        this.hiddenFlags = hiddenFlags;
    }

    /**
     * @param visitorBannedCommands the visitorbannedcommands to set
     */
    public void setVisitorBannedCommands(List<String> visitorBannedCommands) {
        this.visitorBannedCommands = visitorBannedCommands;
    }

    /**
     * @param fallingBannedCommands the fallingBannedCommands to set
     */
    public void setFallingBannedCommands(List<String> fallingBannedCommands) {
        this.fallingBannedCommands = fallingBannedCommands;
    }

    /**
     * @param worldFlags the worldFlags to set
     */
    public void setWorldFlags(Map<String, Boolean> worldFlags) {
        this.worldFlags = worldFlags;
    }

    /**
     * @param worldName the worldName to set
     */
    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }
    /**
     * @return the acidDamageSnow
     */
    public boolean isAcidDamageSnow() {
        return acidDamageSnow;
    }
    /**
     * @param acidDamageSnow the acidDamageSnow to set
     */
    public void setAcidDamageSnow(boolean acidDamageSnow) {
        this.acidDamageSnow = acidDamageSnow;
    }
    /**
     * @return the deathsResetOnNewIsland
     */
    @Override
    public boolean isDeathsResetOnNewIsland() {
        return deathsResetOnNewIsland;
    }
    /**
     * @param deathsResetOnNewIsland the deathsResetOnNewIsland to set
     */
    public void setDeathsResetOnNewIsland(boolean deathsResetOnNewIsland) {
        this.deathsResetOnNewIsland = deathsResetOnNewIsland;
    }
    /**
     * @return the onJoinCommands
     */
    @Override
    public List<String> getOnJoinCommands() {
        return onJoinCommands;
    }
    /**
     * @param onJoinCommands the onJoinCommands to set
     */
    public void setOnJoinCommands(List<String> onJoinCommands) {
        this.onJoinCommands = onJoinCommands;
    }
    /**
     * @return the onLeaveCommands
     */
    @Override
    public List<String> getOnLeaveCommands() {
        return onLeaveCommands;
    }
    /**
     * @param onLeaveCommands the onLeaveCommands to set
     */
    public void setOnLeaveCommands(List<String> onLeaveCommands) {
        this.onLeaveCommands = onLeaveCommands;
    }
    /**
     * @return the onJoinResetHealth
     */
    @Override
    public boolean isOnJoinResetHealth() {
        return onJoinResetHealth;
    }
    /**
     * @param onJoinResetHealth the onJoinResetHealth to set
     */
    public void setOnJoinResetHealth(boolean onJoinResetHealth) {
        this.onJoinResetHealth = onJoinResetHealth;
    }
    /**
     * @return the onJoinResetHunger
     */
    @Override
    public boolean isOnJoinResetHunger() {
        return onJoinResetHunger;
    }
    /**
     * @param onJoinResetHunger the onJoinResetHunger to set
     */
    public void setOnJoinResetHunger(boolean onJoinResetHunger) {
        this.onJoinResetHunger = onJoinResetHunger;
    }
    /**
     * @return the onJoinResetXP
     */
    @Override
    public boolean isOnJoinResetXP() {
        return onJoinResetXP;
    }
    /**
     * @param onJoinResetXP the onJoinResetXP to set
     */
    public void setOnJoinResetXP(boolean onJoinResetXP) {
        this.onJoinResetXP = onJoinResetXP;
    }
    /**
     * @return the onLeaveResetHealth
     */
    @Override
    public boolean isOnLeaveResetHealth() {
        return onLeaveResetHealth;
    }
    /**
     * @param onLeaveResetHealth the onLeaveResetHealth to set
     */
    public void setOnLeaveResetHealth(boolean onLeaveResetHealth) {
        this.onLeaveResetHealth = onLeaveResetHealth;
    }
    /**
     * @return the onLeaveResetHunger
     */
    @Override
    public boolean isOnLeaveResetHunger() {
        return onLeaveResetHunger;
    }
    /**
     * @param onLeaveResetHunger the onLeaveResetHunger to set
     */
    public void setOnLeaveResetHunger(boolean onLeaveResetHunger) {
        this.onLeaveResetHunger = onLeaveResetHunger;
    }
    /**
     * @return the onLeaveResetXP
     */
    @Override
    public boolean isOnLeaveResetXP() {
        return onLeaveResetXP;
    }
    /**
     * @param onLeaveResetXP the onLeaveResetXP to set
     */
    public void setOnLeaveResetXP(boolean onLeaveResetXP) {
        this.onLeaveResetXP = onLeaveResetXP;
    }

    /**
     * @param createIslandOnFirstLoginEnabled the createIslandOnFirstLoginEnabled to set
     */
    public void setCreateIslandOnFirstLoginEnabled(boolean createIslandOnFirstLoginEnabled)
    {
        this.createIslandOnFirstLoginEnabled = createIslandOnFirstLoginEnabled;
    }

    /**
     * @param createIslandOnFirstLoginDelay the createIslandOnFirstLoginDelay to set
     */
    public void setCreateIslandOnFirstLoginDelay(int createIslandOnFirstLoginDelay)
    {
        this.createIslandOnFirstLoginDelay = createIslandOnFirstLoginDelay;
    }

    /**
     * @param createIslandOnFirstLoginAbortOnLogout the createIslandOnFirstLoginAbortOnLogout to set
     */
    public void setCreateIslandOnFirstLoginAbortOnLogout(boolean createIslandOnFirstLoginAbortOnLogout)
    {
        this.createIslandOnFirstLoginAbortOnLogout = createIslandOnFirstLoginAbortOnLogout;
    }

    /**
     * @return the pasteMissingIslands
     * @since 1.10.0
     */
    @Override
    public boolean isPasteMissingIslands() {
        return pasteMissingIslands;
    }

    /**
     * @param pasteMissingIslands the pasteMissingIslands to set
     * @since 1.10.0
     */
    public void setPasteMissingIslands(boolean pasteMissingIslands) {
        this.pasteMissingIslands = pasteMissingIslands;
    }

    /**
     * Get acid rain potion effects
     * @return liust of potion effects
     * @since 1.9.1
     */
    public List<PotionEffectType> getAcidRainEffects() {
        return acidRainEffects;
    }

    /**
     *
     * @param acidRainEffects
     * @since 1.9.1
     */
    public void setAcidRainEffects(List<PotionEffectType> acidRainEffects) {
        this.acidRainEffects = acidRainEffects;
    }
    /**
     * @return the rainEffectDuation
     */
    public int getRainEffectDuation() {
        return rainEffectDuation;
    }
    /**
     * @param rainEffectDuation the rainEffectDuation to set
     */
    public void setRainEffectDuation(int rainEffectDuation) {
        this.rainEffectDuation = rainEffectDuation;
    }
    /**
     * @return the acidEffectDuation
     */
    public int getAcidEffectDuation() {
        return acidEffectDuation;
    }
    /**
     * @param acidEffectDuation the acidEffectDuation to set
     */
    public void setAcidEffectDuation(int acidEffectDuation) {
        this.acidEffectDuation = acidEffectDuation;
    }
    /**
     * @return the spawnLimitMonsters
     */
    public int getSpawnLimitMonsters() {
        return spawnLimitMonsters;
    }
    /**
     * @param spawnLimitMonsters the spawnLimitMonsters to set
     */
    public void setSpawnLimitMonsters(int spawnLimitMonsters) {
        this.spawnLimitMonsters = spawnLimitMonsters;
    }
    /**
     * @return the spawnLimitAnimals
     */
    public int getSpawnLimitAnimals() {
        return spawnLimitAnimals;
    }
    /**
     * @param spawnLimitAnimals the spawnLimitAnimals to set
     */
    public void setSpawnLimitAnimals(int spawnLimitAnimals) {
        this.spawnLimitAnimals = spawnLimitAnimals;
    }
    /**
     * @return the spawnLimitWaterAnimals
     */
    public int getSpawnLimitWaterAnimals() {
        return spawnLimitWaterAnimals;
    }
    /**
     * @param spawnLimitWaterAnimals the spawnLimitWaterAnimals to set
     */
    public void setSpawnLimitWaterAnimals(int spawnLimitWaterAnimals) {
        this.spawnLimitWaterAnimals = spawnLimitWaterAnimals;
    }
    /**
     * @return the spawnLimitAmbient
     */
    public int getSpawnLimitAmbient() {
        return spawnLimitAmbient;
    }
    /**
     * @param spawnLimitAmbient the spawnLimitAmbient to set
     */
    public void setSpawnLimitAmbient(int spawnLimitAmbient) {
        this.spawnLimitAmbient = spawnLimitAmbient;
    }
    /**
     * @return the ticksPerAnimalSpawns
     */
    public int getTicksPerAnimalSpawns() {
        return ticksPerAnimalSpawns;
    }
    /**
     * @param ticksPerAnimalSpawns the ticksPerAnimalSpawns to set
     */
    public void setTicksPerAnimalSpawns(int ticksPerAnimalSpawns) {
        this.ticksPerAnimalSpawns = ticksPerAnimalSpawns;
    }
    /**
     * @return the ticksPerMonsterSpawns
     */
    public int getTicksPerMonsterSpawns() {
        return ticksPerMonsterSpawns;
    }
    /**
     * @param ticksPerMonsterSpawns the ticksPerMonsterSpawns to set
     */
    public void setTicksPerMonsterSpawns(int ticksPerMonsterSpawns) {
        this.ticksPerMonsterSpawns = ticksPerMonsterSpawns;
    }
    /**
     * @return the maxCoopSize
     */
    @Override
    public int getMaxCoopSize() {
        return maxCoopSize;
    }

    /**
     * @param maxCoopSize the maxCoopSize to set
     */
    public void setMaxCoopSize(int maxCoopSize) {
        this.maxCoopSize = maxCoopSize;
    }

    /**
     * @return the maxTrustSize
     */
    @Override
    public int getMaxTrustSize() {
        return maxTrustSize;
    }

    /**
     * @param maxTrustSize the maxTrustSize to set
     */
    public void setMaxTrustSize(int maxTrustSize) {
        this.maxTrustSize = maxTrustSize;
    }
    /**
     * @return the playerCommandAliases
     */
    @Override
    public String getPlayerCommandAliases() {
        return playerCommandAliases;
    }
    /**
     * @param playerCommandAliases the playerCommandAliases to set
     */
    public void setPlayerCommandAliases(String playerCommandAliases) {
        this.playerCommandAliases = playerCommandAliases;
    }
    /**
     * @return the adminCommandAliases
     */
    @Override
    public String getAdminCommandAliases() {
        return adminCommandAliases;
    }
    /**
     * @param adminCommandAliases the adminCommandAliases to set
     */
    public void setAdminCommandAliases(String adminCommandAliases) {
        this.adminCommandAliases = adminCommandAliases;
    }
    /**
     * @return the defaultNewPlayerAction
     */
    @Override
    public String getDefaultNewPlayerAction() {
        return defaultNewPlayerAction;
    }
    /**
     * @param defaultNewPlayerAction the defaultNewPlayerAction to set
     */
    public void setDefaultNewPlayerAction(String defaultNewPlayerAction) {
        this.defaultNewPlayerAction = defaultNewPlayerAction;
    }
    /**
     * @return the defaultPlayerAction
     */
    @Override
    public String getDefaultPlayerAction() {
        return defaultPlayerAction;
    }
    /**
     * @param defaultPlayerAction the defaultPlayerAction to set
     */
    public void setDefaultPlayerAction(String defaultPlayerAction) {
        this.defaultPlayerAction = defaultPlayerAction;
    }
}
