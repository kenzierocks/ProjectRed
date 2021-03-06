package mrtjp.projectred.exploration

import codechicken.microblock.BlockMicroMaterial
import cpw.mods.fml.relauncher.{Side, SideOnly}
import mrtjp.core.block.TileRenderRegistry
import mrtjp.core.color.Colors
import mrtjp.core.gui.GuiHandler
import mrtjp.core.world._
import mrtjp.projectred.ProjectRedExploration._
import mrtjp.projectred.core.{Configurator, IProxy}
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraftforge.client.MinecraftForgeClient
import net.minecraftforge.common.util.EnumHelper
import net.minecraftforge.oredict.OreDictionary

class ExplorationProxy_server extends IProxy
{
    val guiIDBackpack = 1

    override def preinit() {}

    override def init()
    {
        itemWoolGin = new ItemWoolGin
        itemBackpack = new ItemBackpack

        blockOres = new BlockOre
        for (o <- OreDefs.values) blockOres.setHarvestLevel("pickaxe", o.harvest, o.meta)

        blockDecoratives = new BlockDecoratives
        for (b <- DecorativeStoneDefs.values) blockDecoratives.setHarvestLevel("pickaxe", b.harvest, b.meta)

        blockDecorativeWalls = new BlockDecorativeWalls

        blockLily = new BlockLily
        blockLily.addSingleTile(classOf[TileLily])
        itemLilySeed = new ItemLilySeeds
        for (i <- 0 until 16) OreDictionary.registerOre(Colors(i).oreDict, new ItemStack(itemLilySeed, 1, i))

//        if (Configurator.gen_SpreadingMoss) //TODO fix
//        {
////            val mc = Block.cobblestoneMossy.blockID
////            Block.blocksList(mc) = null
////            new BlockPhotosyntheticCobblestone(mc)
////            val sb = Block.stoneBrick.blockID
////            Block.blocksList(sb) = null
////            new BlockPhotosyntheticStoneBrick(sb)
//
//            dynMossyCobble = new BlockDynamicMossyCobble
//            GameRegistry.addSubstitutionAlias("mossy_cobblestone", GameRegistry.Type.BLOCK, dynMossyCobble)
//        }

        toolMaterialRuby =      EnumHelper.addToolMaterial("RUBY",      2, 512, 8.00F, 3.00F, 10)
        toolMaterialSapphire =  EnumHelper.addToolMaterial("SAPPHIRE",  2, 512, 8.00F, 3.00F, 10)
        toolMaterialPeridot =   EnumHelper.addToolMaterial("PERIDOT",   2, 512, 7.75F, 2.75F, 14)

        armorMatrialRuby =      EnumHelper.addArmorMaterial("RUBY",     16, Array(3, 8, 6, 3),  8)
        armorMatrialSapphire =  EnumHelper.addArmorMaterial("SAPPHIRE", 16, Array(3, 8, 6, 3),  8)
        armorMatrialPeridot =   EnumHelper.addArmorMaterial("PERIDOT",  14, Array(3, 8, 6, 3), 10)

        itemRubyAxe = new ItemGemAxe(ToolDefs.RUBYAXE)
        itemSapphireAxe = new ItemGemAxe(ToolDefs.SAPPHIREAXE)
        itemPeridotAxe = new ItemGemAxe(ToolDefs.PERIDOTAXE)

        itemRubyHoe = new ItemGemHoe(ToolDefs.RUBYHOE)
        itemSapphireHoe = new ItemGemHoe(ToolDefs.SAPPHIREHOE)
        itemPeridotHoe = new ItemGemHoe(ToolDefs.PERIDOTHOE)

        itemRubyPickaxe = new ItemGemPickaxe(ToolDefs.RUBYPICKAXE)
        itemSapphirePickaxe = new ItemGemPickaxe(ToolDefs.SAPPHIREPICKAXE)
        itemPeridotPickaxe = new ItemGemPickaxe(ToolDefs.PERIDOTPICKAXE)

        itemRubyShovel = new ItemGemShovel(ToolDefs.RUBYSHOVEL)
        itemSapphireShovel = new ItemGemShovel(ToolDefs.SAPPHIRESHOVEL)
        itemPeridotShovel = new ItemGemShovel(ToolDefs.PERIDOTSHOVEL)

        itemRubySword = new ItemGemSword(ToolDefs.RUBYSWORD)
        itemSapphireSword = new ItemGemSword(ToolDefs.SAPPHIRESWORD)
        itemPeridotSword = new ItemGemSword(ToolDefs.PERIDOTSWORD)

        itemGoldSaw = new ItemGemSaw(ToolDefs.GOLDSAW)
        itemRubySaw = new ItemGemSaw(ToolDefs.RUBYSAW)
        itemSapphireSaw = new ItemGemSaw(ToolDefs.SAPPHIRESAW)
        itemPeridotSaw = new ItemGemSaw(ToolDefs.PERIDOTSAW)

        itemWoodSickle = new ItemGemSickle(ToolDefs.WOODSICKLE)
        itemStoneSickle = new ItemGemSickle(ToolDefs.STONESICKLE)
        itemIronSickle = new ItemGemSickle(ToolDefs.IRONSICKLE)
        itemGoldSickle = new ItemGemSickle(ToolDefs.GOLDSICKLE)
        itemRubySickle = new ItemGemSickle(ToolDefs.RUBYSICKLE)
        itemSapphireSickle = new ItemGemSickle(ToolDefs.SAPPHIRESICKLE)
        itemPeridotSickle = new ItemGemSickle(ToolDefs.PERIDOTSICKLE)
        itemDiamondSickle = new ItemGemSickle(ToolDefs.DIAMONDSICKLE)

        itemRubyHelmet = new ItemGemArmor(ArmorDefs.RUBYHELMET, 0)
        itemRubyChestplate = new ItemGemArmor(ArmorDefs.RUBYCHESTPLATE, 1)
        itemRubyLeggings = new ItemGemArmor(ArmorDefs.RUBYLEGGINGS, 2)
        itemRubyBoots = new ItemGemArmor(ArmorDefs.RUBYBOOTS, 3)

        itemSapphireHelmet = new ItemGemArmor(ArmorDefs.SAPPHIREHELMET, 0)
        itemSapphireChestplate = new ItemGemArmor(ArmorDefs.SAPPHIRECHESTPLATE, 1)
        itemSapphireLeggings = new ItemGemArmor(ArmorDefs.SAPPHIRELEGGINGS, 2)
        itemSapphireBoots = new ItemGemArmor(ArmorDefs.SAPPHIREBOOTS, 3)

        itemPeridotHelmet = new ItemGemArmor(ArmorDefs.PERIDOTHELMET, 0)
        itemPeridotChestplate = new ItemGemArmor(ArmorDefs.PERIDOTCHESTPLATE, 1)
        itemPeridotLeggings = new ItemGemArmor(ArmorDefs.PERIDOTLEGGINGS, 2)
        itemPeridotBoots = new ItemGemArmor(ArmorDefs.PERIDOTBOOTS, 3)

        for (s <- DecorativeStoneDefs.values)
            BlockMicroMaterial.createAndRegister(blockDecoratives, s.meta)

        ExplorationRecipes.initOreDict()
        ExplorationRecipes.initRecipes()

        //World Gen

        //Ruby
        if (Configurator.gen_Ruby)
        {
            val logic = new GenLogicUniform
            logic.name = "pr_ruby"
            logic.resistance = 8+Configurator.gen_Ruby_resistance
            logic.allowRetroGen = Configurator.gen_Ruby_retro
            logic.minY = 12
            logic.maxY = 20
            logic.attempts = 1
            val gen = new WorldGenClusterizer
            gen.cluster = Set(((blockOres, OreDefs.ORERUBY.meta), 1))
            gen.clusterSize = 5
            gen.material = Set((Blocks.stone, 0))
            logic.gen = gen
            SimpleGenHandler.registerStructure(logic)
        }

        //Sapphire
        if (Configurator.gen_Sapphire)
        {
            val logic = new GenLogicUniform
            logic.name = "pr_sapphire"
            logic.resistance = 8+Configurator.gen_Sapphire_resistance
            logic.allowRetroGen = Configurator.gen_Sapphire_retro
            logic.minY = 12
            logic.maxY = 20
            logic.attempts = 1
            val gen = new WorldGenClusterizer
            gen.cluster = Set(((blockOres, OreDefs.ORESAPPHIRE.meta), 1))
            gen.clusterSize = 5
            gen.material = Set((Blocks.stone, 0))
            logic.gen = gen
            SimpleGenHandler.registerStructure(logic)
        }

        //Peridot
        if (Configurator.gen_Peridot)
        {
            val logic = new GenLogicUniform
            logic.name = "pr_peridot"
            logic.resistance = 8+Configurator.gen_Peridot_resistance
            logic.allowRetroGen = Configurator.gen_Peridot_retro
            logic.minY = 16
            logic.maxY = 28
            logic.attempts = 2
            val gen = new WorldGenClusterizer
            gen.cluster = Set(((blockOres, OreDefs.OREPERIDOT.meta), 1))
            gen.clusterSize = 5
            gen.material = Set((Blocks.stone, 0))
            logic.gen = gen
            SimpleGenHandler.registerStructure(logic)
        }

        //Marble
        if (Configurator.gen_MarbleCave)
        {
            val logic = new GenLogicUniform
            logic.name = "pr_marblecave"
            logic.resistance = 4+Configurator.gen_MarbleCave_resistance
            logic.allowRetroGen = Configurator.gen_MarbleCave_retro
            logic.dimensionBlacklist = false
            logic.dimensions = Set(0)
            logic.minY = 32
            logic.maxY = 64
            val gen = new WorldGenCaveReformer
            gen.cluster = Set(((blockDecoratives, DecorativeStoneDefs.MARBLE.meta), 1))
            gen.clusterSize = 1024
            gen.material = Set((Blocks.stone, 0))
            logic.gen = gen
            SimpleGenHandler.registerStructure(logic)
        }

        //Volcano
        if (Configurator.gen_Volcano)
        {
            val logic = new GenLogicUniform
            logic.name = "pr_volcano"
            logic.resistance = 16+Configurator.gen_Volcano_resistance
            logic.allowRetroGen = Configurator.gen_Volcano_retro
            logic.dimensionBlacklist = false
            logic.dimensions = Set(0)
            logic.minY = 0
            logic.maxY = 64
            val gen = new WorldGenVolcanic
            gen.ashCluster = Set(((blockDecoratives, DecorativeStoneDefs.BASALT.meta), 1))
            gen.conduitCluster = gen.ashCluster
            gen.liq = (Blocks.lava, 0)
            gen.materialStart = Set(gen.liq)
            logic.gen = gen
            SimpleGenHandler.registerStructure(logic)
        }

        //Lily
        if (Configurator.gen_Lily)
        {
            val logic = new GenLogicSurface
            logic.name = "pr_lily"
            logic.resistance = 8+Configurator.gen_Lily_resistance //TODO add more
            logic.allowRetroGen = Configurator.gen_Lily_retro
            val gen = new WorldGenDecorator
            gen.cluster = Set(((blockLily, 0), 1))
            gen.material = Set((Blocks.air, 0))
            gen.soil = Set((Blocks.grass, 0), (Blocks.dirt, 0))
            logic.gen = gen
            SimpleGenHandler.registerStructure(logic)
        }
    }

    override def postinit(){}

    override def version = "@VERSION@"
    override def build = "@BUILD_NUMBER@"
}

class ExplorationProxy_client extends ExplorationProxy_server
{
    @SideOnly(Side.CLIENT)
    override def init()
    {
        super.init()
        MinecraftForgeClient.registerItemRenderer(itemGoldSaw, GemSawRenderer)
        MinecraftForgeClient.registerItemRenderer(itemRubySaw, GemSawRenderer)
        MinecraftForgeClient.registerItemRenderer(itemSapphireSaw, GemSawRenderer)
        MinecraftForgeClient.registerItemRenderer(itemPeridotSaw, GemSawRenderer)

        GuiHandler.register(GuiBackpack, guiIDBackpack)

        TileRenderRegistry.setRenderer(blockLily, 0, RenderLily)
    }
}

object ExplorationProxy extends ExplorationProxy_client