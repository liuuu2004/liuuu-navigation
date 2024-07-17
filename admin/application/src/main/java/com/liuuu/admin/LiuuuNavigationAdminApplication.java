package com.liuuu.admin;

/**
 * 后台启动类
 *
 * @Author Liuuu
 * @Date 2024/7/16
 */
@SpringBootApplication(scanBasePackages = "com.liuuu")
@MapperScan("com.liuuu.**.mapper")
public class LiuuuNavigationAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiuuuNavigationAdminApplication.class, args);
        System.out.println("启动!!");
    }

}
