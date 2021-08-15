package org.jdkstack.jdkjson.core.common;

/**
 * 128个ASCII码完整列表.
 *
 * <p>美国信息交换标准代码.
 *
 * <pre>
 *  ASCII编码中第0~31个字符(开头的32个字符)
 *  以及第 127个字符(最后一个字符)都是不可见的(无法显示)但是它们都具有一些特殊功能.
 *  所以称为控制字符(Control Character)或者功能码(Function Code).
 * </pre>
 *
 * @author admin
 */
public final class AsciiV1 {
  /** 00000000 0 00 NUL (NULL) 空字符. */
  public static final int ASCII_0 = 0;
  /** 00000001 1 01 SOH (Start Of Headling) 标题开始. */
  public static final int ASCII_1 = 1;
  /** 00000010 2 02 STX (Start Of Text) 正文开始 . */
  public static final int ASCII_2 = 2;
  /** 00000011 3 03 ETX (End Of Text) 正文结束. */
  public static final int ASCII_3 = 3;
  /** 00000100 4 04 EOT (End Of Transmission) 传输结束. */
  public static final int ASCII_4 = 4;
  /** 00000101 5 05 ENQ (Enquiry) 请求. */
  public static final int ASCII_5 = 5;
  /** 00000110 6 06 ACK (Acknowledge) 回应/响应/收到通知 . */
  public static final int ASCII_6 = 6;
  /** 00000111 7 07 BEL (Bell) 响铃. */
  public static final int ASCII_7 = 7;
  /** 00001000 8 08 BS (Backspace) 退格. */
  public static final int ASCII_8 = 8;
  /** HT (Horizontal Tab) 水平制表符. */
  public static final int ASCII_9 = 9;
  /** LF/NL(Line Feed/New Line) 换行键. */
  public static final int ASCII_10 = 10;
  /** VT (Vertical Tab) 垂直制表符. */
  public static final int ASCII_11 = 11;
  /** FF/NP (Form Feed/New Page) 换页键. */
  public static final int ASCII_12 = 12;
  /** CR (Carriage Return) 回车键 . */
  public static final int ASCII_13 = 13;
  /** SO (Shift Out) 不用切换 . */
  public static final int ASCII_14 = 14;
  /** 00001111 15 0F SI (Shift In) 启用切换. */
  public static final int ASCII_15 = 15;
  /** 00010000 16 10 DLE (Data Link Escape) 数据链路转义. */
  public static final int ASCII_16 = 16;
  /** 00010001 17 11 DC1/XON (Transmission On) 设备控制1/传输开始. */
  public static final int ASCII_17 = 17;
  /** 00010010 18 12 DC2 (Device Control 2) 设备控制2. */
  public static final int ASCII_18 = 18;
  /** 00010011 19 13 DC3/XOFF (Transmission Off) 设备控制3/传输中断. */
  public static final int ASCII_19 = 19;
  /** 00010100 20 14 DC4 (Device Control 4) 设备控制4. */
  public static final int ASCII_20 = 20;
  /** 00010101 21 15 NAK (Negative Acknowledge) 无响应/非正常响应/拒绝接收. */
  public static final int ASCII_21 = 21;
  /** 00010110 22 16 SYN (Synchronous Idle) 同步空闲. */
  public static final int ASCII_22 = 22;
  /** 00010111 23 17 ETB (End of Transmission Block) 传输块结束/块传输终止. */
  public static final int ASCII_23 = 23;
  /** 00011000 24 18 CAN (Cancel) 取消. */
  public static final int ASCII_24 = 24;
  /** 00011001 25 19 EM (End of Medium) 已到介质末端/介质存储已满/介质中断. */
  public static final int ASCII_25 = 25;
  /** 00011010 26 1A SUB (Substitute) 替补/替换 . */
  public static final int ASCII_26 = 26;
  /** 00011011 27 1B ESC (Escape) 逃离/取消 . */
  public static final int ASCII_27 = 27;
  /** 00011100 28 1C FS (File Separator) 文件分割符 . */
  public static final int ASCII_28 = 28;
  /** 00011101 29 1D GS (Group Separator) 组分隔符/分组符. */
  public static final int ASCII_29 = 29;
  /** 00011110 30 1E RS (Record Separator) 记录分离符. */
  public static final int ASCII_30 = 30;
  /** 00011111 31 1F US (Unit Separator) 单元分隔符. */
  public static final int ASCII_31 = 31;
  /** 00100000 32 20 (Space) 空格. */
  public static final int ASCII_32 = 32;
  /** !. */
  public static final int ASCII_33 = 33;
  /** ". */
  public static final int ASCII_34 = 34;
  /** # . */
  public static final int ASCII_35 = 35;
  /** $. */
  public static final int ASCII_36 = 36;
  /** % . */
  public static final int ASCII_37 = 37;
  /** &. */
  public static final int ASCII_38 = 38;
  /** '. */
  public static final int ASCII_39 = 39;
  /** (. */
  public static final int ASCII_40 = 40;
  /** ). */
  public static final int ASCII_41 = 41;
  /** * . */
  public static final int ASCII_42 = 42;
  /** +. */
  public static final int ASCII_43 = 43;
  /** ,. */
  public static final int ASCII_44 = 44;
  /** -. */
  public static final int ASCII_45 = 45;
  /** .. */
  public static final int ASCII_46 = 46;
  /** /. */
  public static final int ASCII_47 = 47;
  /** 0. */
  public static final int ASCII_48 = 48;
  /** 1. */
  public static final int ASCII_49 = 49;
  /** 2. */
  public static final int ASCII_50 = 50;
  /** 3. */
  public static final int ASCII_51 = 51;
  /** 4. */
  public static final int ASCII_52 = 52;
  /** 5. */
  public static final int ASCII_53 = 53;
  /** 6. */
  public static final int ASCII_54 = 54;
  /** 7. */
  public static final int ASCII_55 = 55;
  /** 8. */
  public static final int ASCII_56 = 56;
  /** 9. */
  public static final int ASCII_57 = 57;
  /** :. */
  public static final int ASCII_58 = 58;
  /** ;. */
  public static final int ASCII_59 = 59;
  /** <. */
  public static final int ASCII_60 = 60;
  /** =. */
  public static final int ASCII_61 = 61;
  /** >. */
  public static final int ASCII_62 = 62;
  /** ? . */
  public static final int ASCII_63 = 63;
  /** '@'. */
  public static final int ASCII_64 = 64;
  /** A. */
  public static final int ASCII_65 = 65;
  /** B. */
  public static final int ASCII_66 = 66;
  /** C. */
  public static final int ASCII_67 = 67;
  /** D. */
  public static final int ASCII_68 = 68;
  /** E. */
  public static final int ASCII_69 = 69;
  /** F. */
  public static final int ASCII_70 = 70;
  /** G . */
  public static final int ASCII_71 = 71;
  /** H. */
  public static final int ASCII_72 = 72;
  /** I. */
  public static final int ASCII_73 = 73;
  /** J. */
  public static final int ASCII_74 = 74;
  /** K. */
  public static final int ASCII_75 = 75;
  /** L. */
  public static final int ASCII_76 = 76;
  /** M. */
  public static final int ASCII_77 = 77;
  /** N. */
  public static final int ASCII_78 = 78;
  /** O. */
  public static final int ASCII_79 = 79;
  /** P . */
  public static final int ASCII_80 = 80;
  /** Q. */
  public static final int ASCII_81 = 81;
  /** R . */
  public static final int ASCII_82 = 82;
  /** S . */
  public static final int ASCII_83 = 83;
  /** T. */
  public static final int ASCII_84 = 84;
  /** U . */
  public static final int ASCII_85 = 85;
  /** V. */
  public static final int ASCII_86 = 86;
  /** W. */
  public static final int ASCII_87 = 87;
  /** X. */
  public static final int ASCII_88 = 88;
  /** Y . */
  public static final int ASCII_89 = 89;
  /** Z. */
  public static final int ASCII_90 = 90;
  /** [. */
  public static final int ASCII_91 = 91;
  /** \. */
  public static final int ASCII_92 = 92;
  /** ]. */
  public static final int ASCII_93 = 93;
  /** ^ . */
  public static final int ASCII_94 = 94;
  /** _ . */
  public static final int ASCII_95 = 95;
  /** ` . */
  public static final int ASCII_96 = 96;
  /** a. */
  public static final int ASCII_97 = 97;
  /** b. */
  public static final int ASCII_98 = 98;
  /** c. */
  public static final int ASCII_99 = 99;
  /** d. */
  public static final int ASCII_100 = 100;
  /** e. */
  public static final int ASCII_101 = 101;
  /** f. */
  public static final int ASCII_102 = 102;
  /** g. */
  public static final int ASCII_103 = 103;
  /** h. */
  public static final int ASCII_104 = 104;
  /** i. */
  public static final int ASCII_105 = 105;
  /** j. */
  public static final int ASCII_106 = 106;
  /** k. */
  public static final int ASCII_107 = 107;
  /** l. */
  public static final int ASCII_108 = 108;
  /** m. */
  public static final int ASCII_109 = 109;
  /** n. */
  public static final int ASCII_110 = 110;
  /** o. */
  public static final int ASCII_111 = 111;
  /** p . */
  public static final int ASCII_112 = 112;
  /** q. */
  public static final int ASCII_113 = 113;
  /** r. */
  public static final int ASCII_114 = 114;
  /** s. */
  public static final int ASCII_115 = 115;
  /** t. */
  public static final int ASCII_116 = 116;
  /** u. */
  public static final int ASCII_117 = 117;
  /** v. */
  public static final int ASCII_118 = 118;
  /** w. */
  public static final int ASCII_119 = 119;
  /** x. */
  public static final int ASCII_120 = 120;
  /** y. */
  public static final int ASCII_121 = 121;
  /** z. */
  public static final int ASCII_122 = 122;
  /** { . */
  public static final int ASCII_123 = 123;
  /** |. */
  public static final int ASCII_124 = 124;
  /** }. */
  public static final int ASCII_125 = 125;
  /** ~. */
  public static final int ASCII_126 = 126;
  /** 01111111 127 7F DEL (Delete) 删除. */
  public static final int ASCII_127 = 127;

  private AsciiV1() {
    //
  }
}
