import java.util.Scanner;
public class order {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] names = new String[6]; // 订餐人姓名
        String[] dishMsgs = new String[6];// 选择菜品
        int[] numbers = new int[6];// 菜品份数
        int[] times = new int[6]; // 送餐时间
        String[] addresses = new String[6];// 送餐地址
        int[] states = new int[6];// 订单状态 0:已经预定 1：已经完成
        double[] sumPrices = new double[6];// 总金额
        int[] praiseNums = new int[6];
        String[] dishNames = { "霸王桶Plus","霸王桶","霸王鸡块","超值小食盒A","霸王鸡条","薯霸王" };
        double[] prices = { 88, 52, 36, 15, 13.5, 12 };
        System.out.println("欢迎光临汉堡王");
        System.out.println("Welcome to Burger King");
        System.out.println("新品推出：布朗尼覆盆子风味新地 布朗尼覆盆子风味KING甜筒 KING暴风酷黑奥利奥");
        int num = -1; // 输入0 默认返回主菜单
        boolean isExit = false;// 默认不退出
        do {
            System.out.println("**********************");
            System.out.println("1:我要订餐");
            System.out.println("2:查看餐袋");
            System.out.println("3:签收订单");
            System.out.println("4:删除订单");
            System.out.println("5:我要点赞");
            System.out.println("6:退出系统");
            System.out.println("**********************");
            System.out.println("请选择：");
            int choice = scanner.nextInt(); //选择你所需要的服务
            switch (choice) {
                case 1:
                    System.out.println("************我要订餐************");
                    boolean empty = false;//作一个标记
                    for (int i = 0; i < names.length; i++) { // 订餐人的集合
                        if (names[i] == null) {
                            empty = true;//订单如果是空的，便可以往里加数据
                            System.out.println("请输入订餐人的姓名：");
                            names[i] = scanner.next(); // 获取订餐人的姓名
                            System.out.println("您可以选择下列的菜品：");
                            System.out.println("序号\t\t\t菜名\t\t\t单价\t\t\t点赞数");
                            for (int j = 0; j < dishNames.length; j++) { // 遍历菜品
                                String praiseNum = (praiseNums[j] > 0) ? praiseNums[j]
                                        + "个赞"
                                        : "";
                                System.out.println(j + 1 + "\t\t\t" + dishNames[j]
                                        + "\t\t\t" + prices[j] + "\t\t\t"
                                        + praiseNum);
                            }
                            // 用户开始点菜，并收集信息
                            System.out.println("请您选择菜品的编号：");
                            int choiceName = scanner.nextInt();
                            System.out.println("请您输入点餐的份数：");
                            int number = scanner.nextInt();
                            System.out.println("请您输入送餐的地址：");
                            String address = scanner.next();
                            System.out.println("请您输入送餐的时间：");
                            System.out.println("本店整点送餐：8点至20点");
                            int time = scanner.nextInt();
                            double sumPrice = prices[choiceName - 1] * number;
                            double price = (sumPrice >=50) ? 0 : 5;//判断是否收取运费
                            System.out.println("订单已生成！");
                            System.out.println("您定的菜品是："+ dishNames[choiceName - 1] + "\t" + number+ "份");
                            System.out.print("餐费：" + sumPrice);
                            System.out.print("\t送餐费：" + price);
                            System.out.println("\t总费用：" + (price + sumPrice));
                            // 把输入的信息塞进数组中
                            times[i] = time; // 时间
                            addresses[i] = address; // 地址
                            sumPrices[i] = (sumPrice + price); // 总金额
                            dishMsgs[i] = dishNames[choiceName - 1]; // 定的菜品
                            states[i] = 0; // 订餐的状态
                            numbers[i] = number; // 份数
                            break;
                        }
                    }
                    if (!empty) {// 餐袋已经满了
                        System.out.println("餐袋已经满了");
                    }
                    break;
                case 2:
                    System.out.println("************查看餐袋************");
                    System.out.println("编号\t\t订餐人\t\t菜品(份数)\t\t送餐时间 \t\t送餐地址\t\t总金额\t\t状态\t\t点赞数量");
                    for (int i = 0; i < names.length; i++) {
                        if (names[i] != null) { // 有人订餐 的显示
                            String state = (states[i] == 0) ? "已经预定" : "已经完成"; // 获取状态
                            int time = times[i]; // 时间
                            String sumPrice = sumPrices[i] + "元"; // 总金额
                            String address = addresses[i]; // 地址
                            String dishMsg = dishMsgs[i]; // 菜品
                            int count = numbers[i];//份数
                            System.out.println((i + 1) + "\t\t" + names[i] + "\t\t"+ dishMsg + "(" + count + ")" + "\t\t" + time+ " \t\t" + address + "\t\t" + sumPrice+ "\t\t" + state + "\t\t" + praiseNums[i]);
                        }
                    }
                    break;
                case 3:
                    System.out.println("************签收订单************");
                    System.out.println("请您输入需要签收的订单编号：");
                    int sign = scanner.nextInt();
                    int flag = 0;// 作一个标记，来判断订单是否被签收
                    for (int i = 0; i < names.length; i++) {
                        if (names[i] != null && states[i] == 0) {//有订单并且可以签收
                            states[i] = 1;// 改变状态
                            flag = 1;
                            break;
                        } else if (names[i] != null && states[i] == 1) {//有订单，并且已被签收
                            flag = 2;
                        }
                    }
                    if (flag == 1) {
                        System.out.println("订单已经签收！");
                    } else if (flag == 2) {
                        System.out.println("订单不能重复签收！");
                    } else {
                        System.out.println("没有找到您的订单！请您核对编号！");
                    }


                    break;
                case 4:
                    System.out.println("************删除订单************");
                    System.out.println("请您输入需要删除的订单编号：");
                    int dealNum = scanner.nextInt();
                    int deal = 0; // 作一个标记 来判断订单是否被签收以及是否存在
                    for (int i = 0; i < names.length; i++) {
                        if (names[i] != null && states[dealNum - 1] == 1) { //有订单，并且已签收，可以删除
                            // 删除一条信息，将之后的所有信息，依次向前移
                            for (int j = dealNum - 1; j < names.length - 1; j++) {
                                names[j] = names[j + 1]; // 订餐人
                                dishMsgs[j] = dishMsgs[j + 1];// 菜品
                                times[j] = times[j + 1];// 时间
                                addresses[j] = addresses[j + 1];// 地址
                                states[j] = states[j + 1];// 状态
                                numbers[j] = numbers[j + 1];// 份数
                            }
                            deal = 1;
                            break;
                        } else if (names[i] != null && states[i] == 0) { // 右订单并且订单还没有签收，便不能删除
                            deal = 2;
                        }
                    }
                    if (deal == 1) {
                        System.out.println("订单已经被删除");
                    } else if (deal == 2) {
                        System.out.println("订单还没有签收 不允许删除！");
                    } else {
                        System.out.println("没有找到指定的订单！");
                    }
                    break;
                case 5:
                    System.out.println("************我要点赞***************");
                    System.out.println("编号\t\t\t菜名\t\t\t价格");
                    for (int i = 0; i < dishNames.length; i++) { // 遍历所有的菜名
                        String price = prices[i] + "元"; //价格
                        String name = dishNames[i];//菜名
                        System.out.println((i + 1) + "\t\t\t" + name + "\t\t\t"+ price);
                    }
                    System.out.println("请您输入点赞的菜品编号：");
                    int choiceNum = scanner.nextInt();
                    praiseNums[choiceNum - 1]++;// 点赞数累加
                    System.out.println("谢谢参与！点赞成功！");
                    break;
                case 6:
                    // 退出系统
                    isExit = true;
                    break;
                default:
                    // 退出系统
                    isExit = true;
                    break;
            }
            if (!isExit) { // 默认这是不退出
                System.out.println("输入0返回主菜单！");
                num = scanner.nextInt();
            } else {
                break; // 退出系统
            }
        } while (num == 0);
        System.out.println("谢谢您的光临！！！！！");
    }
}