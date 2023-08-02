package net.rainneon.minedog.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
//阅读方式：注释写在被注释代码行的上面
//创建一个自定义的金属探测器物品类继承于原本的物品类
public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Settings settings) {
        super(settings);
    }

    //useOnBlock当在方块上使用物品时就会被调用
    //ItemUsageContext 这个对象包含的内容有方块上使用物品的 玩家，手，方块被击中的结果（包含的容：（被击中的）面，方块的坐标）
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        //context.getWorld().isClient()返回是否为客户端，这里取反，则不在客户端中处理if中的内容，即只在服务器中处理
        if(!context.getWorld().isClient()) {
            //获取方块坐标
            BlockPos positionClicked = context.getBlockPos();
            //获取玩家对象
            PlayerEntity player = context.getPlayer();
            //定义一个发现方块的布尔量
            boolean foundBlock = false;
            //循环，从i=0开始，每次+1，直到i大于被点击方块的Y轴坐标+64时跳出循环(结合下面的positionClicked.down(i)，即检测-64到方块的高度)
            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                //BlockState （方块的状态）对象
                //getWorld()获取所处世界
                //getBlockState获取BlockState对象
                //positionClicked.down(i)点击坐标向下偏移i个（Y轴）单位，即Y轴坐标减i
                BlockState state = context.getWorld().getBlockState(positionClicked.down(i));
                //isValuableBlock()是这个MetalDetectorItem类自定义的方法，作用见下文注释
                if(isValuableBlock(state)) {
                    //outputValuableCoordinates这个MetalDetectorItem类自定义的方法，作用见下文注释
                    outputValuableCoordinates(positionClicked.down(i), player, state.getBlock());
                    //使发现方块的布尔量为true
                    foundBlock = true;

                    break;
                }
            }
            //如果没发现有价值的方块就对玩家发送没有发现的消息
            if(!foundBlock) {
                //sendMessage()发送Text对象给玩家，Text.literal()把字符串转为Text对象的
                player.sendMessage(Text.literal("No Valuables Found!"));
            }
        }

        //getStack获取物品对象，即手持的物品，damage扣除耐久，
        //第二个传入的参数为entity，他在damage方法的参数中被定义为T，这里使用了 context.getPlayer()获取玩家实体 传入，即 T = context.getPlayer()
        //第三个传入参数的类型为 Consumer<T>是Java中的一个函数式接口，用于表示接受一个参数并执行某些操作的函数。它定义了一个名为accept的抽象方法，该方法接受一个类型为T的参数，没有返回值（即void）。
        //简言之 Consumer相当于一个类，里面包含了方法accept()，当你传入这个对象时需要自己写上accept方法，而damage()在执行时会以某种方式调用这个方法
        //这里使用lambda表达式实现了其accept方法 即 playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()
        //playerEntity玩家实体是一个LivingEntity生物实体对象，这里调用里面的方法sendToolBreakStatus发送工具坏掉的状态，
        //sendToolBreakStatus传入的参数类型为Hand，这里使用playerEntity.getActiveHand()获取玩家行动的那只手
        context.getStack().damage(1, context.getPlayer(),
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));

        //返回useOnBlock使用在方块上的ActionResult行动结果为成功
        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        //使用PlayerEntity类的sendMessage方法，给玩家发送消息，block.asItem()返回这个方块的物品对象，getName().getString()获取名字并转为字符串
        //blockPos.getX(),即获取这个快位置对象的x坐标，其他同理
        player.sendMessage(Text.literal("Found " + block.asItem().getName().getString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), false);
    }

    private boolean isValuableBlock(BlockState state) {
        //判断方块是否是有价值的方块，这里使用正则表达式写了返回信息
        //isOf是AbstractBlock抽象方块类中的方法，传入参数为方块对象，作用是检测快状态是否为指定方块并返回布尔量
        return state.isOf(Blocks.IRON_ORE) || state.isOf(Blocks.DIAMOND_ORE);
    }
}