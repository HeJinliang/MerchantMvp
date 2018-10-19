package com.i3020.mvpdemo01.config;

/**
 * describe: 公共常量
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/7/3.
 */

public class Constant {

    public static String Cipher_Content = "RSA/ECB/PKCS1Padding";//Cipher实例化参数  算法名称/模式/填充方式5a535551b27b0a315c0001b0/***************************************** 友盟相关 start ***************************************/



    /***************************************** 友盟相关 start ***************************************/

    /**
     * APP_KEY
     */
    public static String UMENG_APP_KEY = "5a535551b27b0a315c0001b0";

    /**
     * Push推送业务的secret
     */
    public static String UMENG_PUSH_SECRET = "fd296dc1774959ec407802972832e00d";


    /****************************************** 友盟相关 end ****************************************/



    /***************************************** 联网字段 start ***************************************/

    /**
     * 切换测试、线上环境
     */
    public static final String URL_LOGIN_CHANGE_ENVIR = "yunlogin/setting";

    /**
     * 一些APP配置信息
     */
    public static final String URL_APP_CONFIG = "common/get_config";

    /**
     * 登录-检查版本更新
     */
    public static final String URL_LOGIN_CHECK_VERSION = "yunlogin/check_version";

    /**
     * 校验登录
     */
    public static final String URL_LOGIN_CHECK_LOGIN = "yunlogin/check_login";

    /**
     * 刷新token
     */
    public static final String URL_LOGIN_UPDATE_TOKEN = "yunlogin/update_token";
    /**
     * 获取公钥
     */
    public static final String URL_GET_PUBLICKEY = "yunlogin/get_publickey";

    /**
     * 获取安全密码的公钥
     */
    public static final String URL_GET_SAFE_PSW_PUBLICKEY = "authority/get_publickey";

    /**
     * 登录-登录
     */
//    public static final String URL_LOGIN_LOGIN = "login";
    public static final String URL_LOGIN_LOGIN = "yunlogin/pwd_login";

    /**
     * 获取神采模块的显示权限
     */
    public static final String URL_LOGIN_CHECK_AUTH = "yunlogin/check_auth";

    /**
     * 首页数据
     */
    public static final String URL_HOME_DATA = "authority/get_share_auths";

    /**
     * 订单管理-获取订单列表信息
     */
    public static final String URL_ORDER_GET_ORDER = "order/get_purchase_order";


    /**
     * 订单管理-获取订单详情
     */
    public static final String URL_ORDER_GET_ORDER_DETAIL = "order/get_order_details";

    /**
     * 订单管理-获取售后单详情
     */
    public static final String URL_ORDER_GET_AFTER_SERVICE_DETAIL = "after_service/inspection_detail";

    /**
     * 补货单详情
     */
    public static final String URL_ORDER_REPLENISHMENT_DETAIL = "after_service/supply_order_detail";
    /**
     * 退货单详情
     */
    public static final String URL_ORDER_GOODS_REJECTED_DETAIL = "after_service/return_order_detail";

    /**
     * 订单管理-确认收货
     */
    public static final String URL_ORDER_CONFIRM_DETAIL = "order/confirm_order";

    /**
     * 订单管理-售后-获取售后列表
     */
    public static final String URL_ORDER_GET_SERVICE = "after_service/get_list";

    /**
     * 订单管理-验货-获取验货信息
     */
    public static final String URL_ORDER_GET_TEST_INFO = "after_service/get_check_info";
    /**
     * 订单管理-验货-提交单个礼品的信息
     */
    public static final String URL_ORDER_TEST_SIGNLE_INFO = "after_service/confirm_check";

    /**
     * 订单管理-验货-提交验货
     */
    public static final String URL_ORDER_COMMIT_TEST_INFO = "order/check_order";

    /**
     * 订单管理-验货-添加礼品
     */
    public static final String URL_ORDER_TEST_ADD_GIFT = "after_service/submit_other_gift";

    /**
     * 库存管理-出库明细
     */
    public static final String URL_DEPOT_OUT = "store_app/get_outhouse_details";

    /**
     * 库存管理-入库明细
     */
    public static final String URL_DEPOT_IN = "store_app/get_inhouse_details";

    /**
     * 库存管理-实时库存
     */
    public static final String URL_DEPOT_CURRENT = "store_app/instance_house";

    /**
     * 仓库管理-进销存
     */
    public static final String URL_DEPOT_DETAIL = "store_app/store_invoicing";

    /**
     * 机台管理-机台展示
     */
    public static final String URL_MACHINE_SHOW = "store_app/get_store_machine_for_display";

    /**
     * 机台管理-机台明细
     */
    public static final String URL_MACHINE_DETAIL = "machine/get_machine_gift_play_details";

    /**
     * 机台管理-获取门店机台编号列表
     */
    public static final String URL_MACHINE_NO_LIST = "store_app/get_store_machine";

    /**
     * 获取所有机台列表 （添加机台）
     */
    public static final String URL_GET_ALL_MACHINE_LIST = "machine_operate/get_machine_list";
    /**
     * 获取门店某机台的信息（添加礼品需要）
     */
    public static final String URL_GET_MACHINE_INFO = "machine_operate/get_machine_info";

    /**
     * 添加机台
     */
    public static final String URL_ADD_MACHINE = "machine_operate/submit_machine_info";

    /**
     * 获取门店所有机台列表（报修）
     */
    public static final String URL_GET_ALL_STORE_MACHINE_LIST = "machine_operate/get_store_all_machine";

    /**
     * 机台管理-获取门店机台可配置礼品列表
     */
    public static final String URL_MACHINE_GIFT_LIST = "store_app/get_store_gifts_for_conf";

    /**
     * 机台管理-获取机台配置礼品信息
     */
    public static final String URL_MACHINE_GIFT_CONFIG_INFO = "machine/get_machine_gift_conf";

    /**
     * 剪刀机 配置礼品信息
     */
    public static final String URL_MACHINE_CLIPPER_CONFIG_INFO = "machine_operate/get_shears_machine_config";

    /**
     * 机台管理-配置礼品
     */
    public static final String URL_MACHINE_GIFT_CONFIG = "machine/deploy_machine_gift";

    /**
     * 机台管理-礼品补货
     */
    public static final String URL_MACHINE_GIFT_ADD = "machine/machine_replenishment";

    /**
     * 机台管理-机台报修结果
     */
    public static final String URL_MACHINE_REPAIR_RESULT = "machine/get_machine_repaired";

    /**
     * 机台报修详情
     */
    public static final String URL_MACHINE_REPAIR_DETAIL = "machine/get_machine_repaire_details";

    /**
     * 机台管理-评价报修结果
     */
    public static final String URL_MACHINE_REPAIR_ASSESS = "machine/issue_comment_for_machine_reparied";

    /**
     * 机台管理-机台报修
     */
    public static final String URL_MACHINE_REPAIR = "machine/repaire_machine";

    /**
     * 个人中心-用户信息
     */
    public static final String URL_USER_INFO = "employee/get_employee";

    /**
     * 个人中心-权限控制
     */
    public static final String URL_USER_AUTHORTY = "authority/get_mine";

    /**
     * 个人中心-用户信息编辑
     */
    public static final String URL_USER_INFO_EDIT = "employee/edit_employee";

    /**
     * 个人中心-用户反馈
     */
    public static final String URL_USER_ADVICE = "employee/feedback";

    /**
     * 收入明细
     */
    public static final String URL_INCOME_DETAIL = "store_app/get_store_income_play_details";

    /**
     * 获取注册验证码
     */
    public static final String URL_REGISTER_GET_CODE = "yunlogin/send_register_code";

    /**
     * 获取重置密码验证码
     */
    public static final String URL_RESET_PSW_GET_CODE = "yunlogin/send_resetpwd_code";

    /**
     * 验证验证码
     */
    public static final String URL_REGISTER_TEST_CODE = "yunlogin/verify_register_code";

    /**
     * 注册
     */
    public static final String URL_REGISTER_USER = "yunlogin/register";

    /**
     * 重置密码
     */
    public static final String URL_RESETTING_PSW = "yunlogin/set_passwd";

    /**
     * 申请门店
     */
    public static final String URL_APPLY_STORE = "employee/create_store_intention";

    /**
     * 获取之前申请门店的申请信息
     */
    public static final String URL_GET_APPLY_INFO = "employee/get_user_intention";

    /**
     * 获取消息列表
     */
    public static final String URL_GET_NOTICE_LIST = "employee/get_user_msg";

    /**
     * 消息分类
     */
    public static final String URL_GET_NOTICE_CATEGORY = "employee/get_msg_category";


    /**
     * 更改消息状态，变为已读
     */
    public static final String URL_CHANGE_MSG_STATUS = "employee/change_msg_status";

    /**
     * 获取市一级地区列表
     */
    public static final String URL_GET_CHILD_DISTRICT = "employee/get_child_district";

    /**
     * 获取区一级地区列表
     */
    public static final String URL_GET_CHILD_DISTRICT2 = "employee/get_child_district2";

    /**
     * 退款记录
     */
    public static final String URL_REFUND_DETAIL = "store_app/get_refund_details";

    /**
     * 订单管理-申请售后
     */
//    public static final String URL_AFTER_SERVICE_APPLY = "order/create_gift_aftermarket_order";
    public static final String URL_AFTER_SERVICE_APPLY = "after_service/apply_inspection";

    /**
     * 订单管理-申请详情
     */
    public static final String URL_AFTER_SERVICE_DETAI = "order/get_gift_aftermarket_order";

    /**
     * 订单管理-礼品调拨列表
     */
    public static final String URL_ORDER_GET_ALLOT = "order/get_transfer_orders";

    /**
     * 订单管理-机台调拨列表
     */
    public static final String URL_ORDER_GET_MACHINE_ALLOT = "store_app/get_machine_transfer_order";

    /**
     * 订单管理-礼品调拨单详情
     */
    public static final String URL_ORDER_ALLOT_DETAIL = "order/get_transfer_order_details";

    /**
     * 订单管理-机台调拨单详情
     */
    public static final String URL_ORDER_MACHINE_ALLOT_DETAIL = "store_app/get_machine_transfer_order_detail";

    /**
     * 订单管理-调拨订单-确认寄出
     */
    public static final String URL_ORDER_SEND_OUT = "order/send_out";

    /**
     * 售后-退货单-确认寄出
     */
    public static final String URL_SERVICE_ORDER_SEND_OUT = "after_service/return_confirm_delivery";

    /**
     * 订单管理-机台调拨订单-确认寄出
     */
    public static final String URL_MACHINE_ORDER_SEND_OUT = "store_app/confirm_machine_out";

    /**
     * 订单管理-调拨订单-反馈问题
     */
    public static final String URL_ORDER_FEEDBACK_PROBLEM = "order/feedback_transfer_orders";

    /**
     * 订单管理-调拨订单-确认收货
     */
    public static final String URL_ORDER_CONFIRM_TRANSFER_ORDER = "order/confirm_transfer_order";

    /**
     * 售后-补货单-确认收货
     */
    public static final String URL_SERVICE_ORDER_CONFIRM_GET = "after_service/supply_order_confirm";

    /**
     * 订单管理-机台调拨单-确认入库
     */
    public static final String URL_MACHINE_ORDER_CONFIRM = "store_app/confirm_machine_in";

    /**
     * 校准可抓取数
     */
    public static final String URL_ORDER_CHANGE_DRAG_NUM = "Machine/just_machine_gift_num";

    /**
     * 实时库存-校准
     */
    public static final String URL_DEPOT_CHANGE = "store_app/submit_just_order";

    /**
     * 实时库存-校准-详情
     */
    public static final String URL_DEPOT_CHANGE_DETAIL = "store_app/get_gift_house";

    /**
     * 实时库存-校准-明细
     */
    public static final String URL_DEPOT_CHANGE_LIST = "store_app/get_just_order_details";

	/**
	 * 实时库存-调整记录
	 */
	public static final String URL_GIFT_ADJUSTJ_RECORD = "store_app/gift_adjust_record";

    /**
     * 实时库存-校准-明细-详情
     */
    public static final String URL_DEPOT_CHANGE_LIST_DETAIL = "store_app/get_just_order";

    /**
     * 反馈详情
     */
    public static final String URL_GET_FEEDBACK = "employee/get_feedback";

    /**
     * 阅读全部
     */
    public static final String URL_NOTICE_READ_ALL = "employee/read_category_all_msg";

    /**
     * 获取门店信息
     */
    public static final String URL_GET_STORE_INFO = "store_app/get_store";

    /**
     * 保存门店信息
     */
    public static final String URL_SAVE_STORE_INFO = "store_app/save_store_principal";

    /**
     * 申请关店
     */
    public static final String URL_STORE_APPLY_CLOSE = "store_app/create_store_close_application";

    /**
     * 钱包管理-获取设置密码 验证码
     */
    public static final String URL_WALLET_GET_CODE = "wallet/send_wallet_setpwd_code";

    /**
     * 钱包管理-验证商户信息
     */
    public static final String URL_WALLET_CHECK_MERCHANT = "wallet/check_merchant";

    /**
     * 钱包管理-设置、重置安全密码
     */
    public static final String URL_WALLET_SET_PSW = "wallet/set_wallet_secure_pwd";

    /**
     * 钱包管理-获取钱包信息
     */
    public static final String URL_GET_STORE_WALLET = "wallet/get_store_wallet";

    /**
     * 钱包管理-获取账户管理信息
     */
//    public static final String URL_WALLET_GET_ACCOUNT_INFO = "wallet/get_bank_cards";
    public static final String URL_WALLET_GET_ACCOUNT_INFO = "wallet/get_account_info";

    /**
     * 钱包管理-解绑银行卡
     */
    public static final String URL_WALLET_UNBIND_BANK_CARD = "wallet/unbundled_bank_card";

    /**
     * 根据银行卡号获取开户行信息
     */
    public static final String URL_WALLET_GET_BANK_INFO = "employee/recognize_card_bank";

    /**
     * 钱包管理-新增账户-获取验证码
     */
    public static final String URL_WALLET_ADD_ACCOUNT_GET_CODE = "wallet/send_bank_bundle_code";

    /**
     * 钱包管理-新增账户
     */
    public static final String URL_WALLET_ADD_ACCOUNT = "wallet/create_bank_account";

    /**
     * 获取商户信息 名称
     */
    public static final String URL_GET_STORE_NAME = "store_app/get_merchant";

    /**
     * 钱包管理-获取支持的银卡列表
     */
    public static final String URL_GET_BANK_LIST = "employee/get_banks";

    /**
     * 验证安全密码
     */
    public static final String URL_WALLET_CHECK_PSW = "wallet/check_wallet_passwd";

    /**
     * 钱包管理-进行提现
     */
    public static final String URL_WALLET_WITHDRAW = "wallet/create_store_withdraw";

    /**
     * 钱包管理-往来明细-收入
     */
    public static final String URL_WALLET_GET_CRONTAB_INCOME = "store_app/get_store_crontab_income";

	/**
	 * 钱包管理-营业收入
	 */
	public static final String URL_WALLET_INCOME_LIST = "wallet_manage/operate_income_list";

    /**
     * 钱包管理-往来明细-支出
     */
    public static final String URL_WALLET_GET_WITHDRAW_INCOME = "store_app/get_store_withdraw";

    /**
     * 门店运营-赔币
     */
    public static final String URL_BUSINESS_COMPENSATE = "loss_coins/info";

    /**
     * 门店运营-使用赔币
     */
    public static final String URL_BUSINESS_PAY_PLAY = "loss_coins/add_order";

    /**
     * 门店运营-赔币明细
     */
    public static final String URL_BUSINESS_COMPENSATE_LIST = "loss_coins/loss_coins_detail";

    /**
     * 钱包管理-选择账户-保存用户的选择
     */
    public static final String URL_WALLET_UPDATE_SELECT_HISTORY = "wallet/update_last_select_card";

    /**
     * 钱包管理-现金收入
     */
    public static final String URL_WALLET_CASH_INCOME = "store_app/get_tkt_in";

    /**
     * 机台管理-购票机列表
     */
    public static final String URL_MACHINE_BUY_TICKET_MANAGER = "store_app/tkt_machine";

    /**
     * 机台管理-购票机-更换纸张
     */
    public static final String URL_MACHINE_BUY_TICKET_CHANGE_PAGE = "store_app/change_ticket_num";

    /**
     * 机台管理-购票机-明细
     */
    public static final String URL_MACHINE_BUY_TICKET_DETAIL = "store_app/tkt_order_statistic";

    /**
     * 钱包管理-往来明细-支出详情
     */
    public static final String URL_WALLET_INCOME_DETAIL = "store_app/get_store_withdraw_detail";

    /**
     * 钱包管理-往来明细-收入详情
     */
    public static final String URL_WALLET_INCOME_IN_DETAIL = "wallet_manage/get_store_daily_detail";

    /**
     * 营收明细-折线图
     */
    public static final String URL_INCOME_DETAIL_CHART = "store_income/get_store_inoutcome_statistics";
    /**
     * 往来明细-折线图
     */
    public static final String URL_WALLET_INCOME_DETAIL_CHART = "store_income/get_wallet_inoutcome";

    /**
     * 现金明细-折线图
     */
    public static final String URL_WALLET_CASH_INCOME_CHART = "store_income/get_cash_statistics";


    /**
     * 机台管理-礼品机台详情页
     */
    public static final String URL_MACHINE_GFIT_STATISTICS = "machine_operate/gift_machine_statistics";

    /**
     * 机台管理-购票机台详情页
     */
    public static final String URL_MACHINE_TICKET_STATISTICS = "machine_operate/tkt_machine_statistics";

    /**
     * 调拨单权限管理
     */
    public static final String URL_ORDER_TRANSFER_AUTH = "order/transfer_auth";

    /**
     * 礼品搜索
     */
    public static final String URL_GET_SEARCH_GIFT = "common/get_search_gift";

    /**
     * 添加机台 获取区域信息
     */
    public static final String URL_GET_AREA_INFO = "machine_operate/get_area_info";

    /**
     * 剪刀机 礼品数据
     */
    public static final String URL_CLIPPER_MACHINE_GIFT = "machine_operate/get_shears_gifts";

    /**
     * 剪刀机 提交数据
     */
    public static final String URL_CLIPPER_MACHINE_SUBMIT = "machine_operate/submit_shears_machine_config";

	/**
	 * 绑定银行卡账户
	 */
	public static final String URL_BIND_BANK_ACCOUNT = "wallet/submit_bank_account";

	/**
	 * 验证提现账户
	 */
	public static final String URL_TEST_VERIFY_BANK = "wallet/verify_bank_account";

	/**
	 * 提现统计
	 */
	public static final String URL_WALLET_WITHDRAW_STATISTICS = "wallet/get_withdraw_statistics";

	/**
	 * 钱包管理-支付明细
	 */
	public static final String URL_WALLET_PAYMENT_STATISTICS = "wallet_manage/payment_statistics";

	/**
	 * 获取提现信息
	 */
	public static final String URL_WALLET_WITHDRAW_INFO = "wallet/get_withdraw_info";

	/**
	 * 平台服务费列表
	 */
	public static final String URL_WALLET_PLATFORM_FEE_LIST = "wallet_manage/platform_fee_list";

	/**
	 * 提现列表
	 */
	public static final String URL_WALLET_WITHDRAW_LIST = "wallet_manage/withdraw_list";

	/**
	 * 门店运营-场地运营
	 */
	public static final String URL_STORE_SITE_OPERATE = "store_operate/site_operate";

	/**
	 * 门店运营-人员管理
	 */
	public static final String URL_STORE_PERSON_MANAGE = "store_operate/person_manage";

	/**
	 * 退出登录
	 */
	public static final String URL_LOGIN_OUT = "login/login_out";

	/**
	 * 获取距门店的距离
	 */
	public static final String URL_GET_DISTANCE = "common/get_distance";

	/**
	 * 客服详情
	 */
	public static final String URL_CUSTOM_SERVICE_DETAIL = "customer_service/customer_feedback_detail";

	/**
	 * 受理客服
	 */
	public static final String URL_CUSTOM_SERVICE_ACCEPT = "customer_service/accept_feedback";

	/**
	 * 处理客服
	 */
	public static final String URL_CUSTOM_SERVICE_DEAL = "customer_service/deal_feedback";


    /****************************************** 联网字段 end ****************************************/




}
