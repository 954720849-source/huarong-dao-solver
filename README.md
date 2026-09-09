# 华容道求解器（Klotski Solver）

一个基于 BFS（广度优先搜索）算法的华容道求解 Android 应用。

## 功能特性

- 🎮 华容道自动求解
- 📊 显示求解步数
- ➡️ 逐步查看求解过程
- 🚀 使用 BFS 算法确保找到最优解

## 项目结构

```
KlotskiSolver/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/klotski/
│   │       │   ├── MainActivity.java       # 主界面
│   │       │   └── KlotskiSolver.java      # 求解核心算法
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   └── activity_main.xml   # UI 布局
│   │       │   ├── values/
│   │       │   │   └── strings.xml
│   │       │   └── drawable/
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── settings.gradle
└── build.gradle
```

## 技术栈

- **语言**: Java
- **平台**: Android
- **最小 SDK**: 21
- **目标 SDK**: 33
- **算法**: 广度优先搜索（BFS）

## 构建 APK

### 方法 1：使用 Android Studio
1. 打开 Android Studio
2. 选择 File → Open → 选择项目文件夹
3. 等待 Gradle 同步完成
4. 点击 Build → Build Bundle(s) / APK(s) → Build APK(s)
5. APK 文件会生成在 `app/build/outputs/apk/debug/` 或 `app/build/outputs/apk/release/`

### 方法 2：使用命令行
```bash
# 构建 Debug APK
./gradlew assembleDebug

# 构建 Release APK
./gradlew assembleRelease
```

生成的 APK 文件位置：
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release.apk`

## 安装 APK

```bash
# 通过 adb 安装到连接的设备或模拟器
adb install -r app-debug.apk
```

## 算法说明

本项目使用 BFS（广度优先搜索）算法求解华容道：

1. **状态表示**: 使用二维数组表示棋盘状态
2. **状态转移**: 通过与空白位置相邻的方块进行交换
3. **搜索策略**: 从初始状态开始，逐层搜索，直到找到目标状态
4. **最优性**: BFS 保证找到最少步数的解决方案

## 开发环境

- Android Studio Flamingo 或更新版本
- JDK 11 或更新版本
- Android SDK 33

## 许可证

MIT License

## 贡献

欢迎提交 Issue 和 Pull Request！
