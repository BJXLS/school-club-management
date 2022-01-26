package com.egao.common.core;

import java.io.File;

/**
 * 系统常量
 * Created by wangfan on 2019-10-29 15:55
 */
public class Constants {
    /* 文件服务器配置 */
    public static final String UPLOAD_DIR = File.listRoots()[0] + "/upload/";  // 上传的目录
    public static final boolean UPLOAD_UUID_NAME = false;  // 文件上传是否用uuid命名
    // OpenOffice在不同操作系统上的安装路径
    public static final String OPEN_OFFICE_PATH_WINDOWS = "C:/OpenOffice";
    public static final String OPEN_OFFICE_PATH_LINUX = "/opt/openoffice.org3";
    public static final String OPEN_OFFICE_PATH_MAC = "/Applications/OpenOffice.org.app/Contents/";

    /* 返回结果统一 */
    public static final int RESULT_OK_CODE = 0;  // 默认成功码
    public static final int RESULT_ERROR_CODE = 1;  // 默认失败码


}
