package com.i3020.mvpdemo01.event;

/**
 * describe: EventBus相关配置文件
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/8/22.
 */

public class EventBusConfig {


    public static final int EVENT_USER_PHOTO_CHANGE = 10001; //用户更换头像
    public static final int EVENT_MACHINE_REPAIR_SUCCESS = 10002;//机台报修成功
    public static final int EVENT_REGISTER_PUSH = 10003;//注册推送
    public static final int EVENT_RECEIVE_PUSH = 10004;//收到推送
    public static final int EVENT_HEARTBEAT_FRAME = 10005;//心跳帧
    public static final int EVENT_USER_NAME_CHANGE = 10006;//用户更改名字
    public static final int EVENT_REFRESH_HOME_DATA = 10007;//切换首页门店，刷新数据

    public static final int EVENT_ORDER_DETAIL_REFRESH = 10008;//订单详情页刷新数据
    public static final int EVENT_REFRESH_NOTICE_DATA = 10009;//刷新消息界面数据
    public static final int EVENT_REFRESH_NOTICE_LIST_DATA = 10011;//刷新消息列表界面数据
    public static final int EVENT_SET_NOTICE_COUNT = 10010;//更改MainActivity消息数量
    public static final int EVENT_ALLOT_CHANGE_DATA = 10011;//更改调拨单详情页AllotDetailActivity数据
    public static final int EVENT_REPAIR_NOTICE_CHANGE_DATA = 10012;//更改报修消息详情页AdviceReplyActivity数据
    public static final int EVENT_REPAIR_DETAIL_CHANGE_DATA = 10013;//更改保修单详情页MachineRepairDetailActivity数据
    public static final int EVENT_DEPOT_CHANGE_CHANGE_DATA = 10014;//更改调整记录页面DepotChangeDetailActivity数据
    public static final int EVENT_ORDER_DETAIL_CHANGE_DATA = 10015;//更改订单管理详情页OrderDetailActivity数据
    public static final int EVENT_STORE_CHANGE_INFO = 10016;//更改门店详情信息
    public static final int EVENT_REFRESH_ACCOUNT_MANAGE = 10017;//刷新账户管理界面数据
    public static final int EVENT_REFRESH_WITHDRAW = 10018;//刷新提现界面数据
    public static final int EVENT_REFRESH_WALLET_JUMP_WITHDRAW = 10019;//刷新钱包管理界面，并准备跳转到提现界面
    public static final int EVENT_WALLET_INCOME_RESETTING = 10020;//往来明细-收入筛选条件
    public static final int EVENT_WALLET_OUTCOME_RESETTING = 10021;//往来明细-支出筛选条件
    public static final int EVENT_REFRESH_HOME_DATA_2 = 10022;//刷新首页数据
    public static final int EVENT_FINISH_WITHDRAW = 10023;//关闭提现界面
    public static final int EVENT_ALLOT_MACHINE_REFRESH = 10024;//刷新机台调拨单界面
    public static final int EVENT_ALLOT_GIFT_REFRESH = 10025;//刷新礼品调拨单界面
    public static final int EVENT_REFRESH_WITHDRAW_SELECT_ACCOUNT = 10026;//刷新提现-选择账户界面数据
    public static final int EVENT_FINISH_WALLET_ACCOUNT_ADD = 10027;//关闭新增账户1界面

    public static final int EVENT_WALLET_INCOME_FILTER = 10028;//往来明细-收入筛选条件选择
    public static final int EVENT_WALLET_OUTCOME_FILTER = 10029;//往来明细-支出筛选条件选择
    public static final int EVENT_ORDER_LIST_REFRESH_DATA = 10030;//订单列表-刷新数据
    public static final int EVENT_REFRESH_USER_AUTHORTY = 10031;//个人中心-权限刷新数据
    public static final int EVENT_REFRESH_MACHINE_INFO = 10032;//机台详情-刷新数据
    public static final int EVENT_CHANGE_ADD_GIFT_ACTIVITY = 10033;//更改补货界面数据
    public static final int EVENT_REFRESH_ACCOUNT_WITHDRAW = 10034;//刷新提现详情界面数据

	public static final int EVENT_UPDATE_UPLOAD_PROGRESS = 10035;//更新下载进度

}
