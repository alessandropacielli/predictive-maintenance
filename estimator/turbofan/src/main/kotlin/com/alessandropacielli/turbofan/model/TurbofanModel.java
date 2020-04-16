package com.alessandropacielli.turbofan.model;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "normalized", database = "test")
public class TurbofanModel {
    private @Column(name = "time")
    Instant time;
    private @Column(name = "1")
    Long cycle;
    private @Column(name = "2")
    Long setting1;
    private @Column(name = "3")
    Long setting2;
    private @Column(name = "4")
    Long setting3;
    private @Column(name = "5")
    Long s1;
    private @Column(name = "6")
    Long s2;
    private @Column(name = "7")
    Long s3;
    private @Column(name = "8")
    Long s4;
    private @Column(name = "9")
    Long s5;
    private @Column(name = "10")
    Long s6;
    private @Column(name = "11")
    Long s7;
    private @Column(name = "12")
    Long s8;
    private @Column(name = "13")
    Long s9;
    private @Column(name = "14")
    Long s10;
    private @Column(name = "15")
    Long s11;
    private @Column(name = "16")
    Long s12;
    private @Column(name = "17")
    Long s13;
    private @Column(name = "18")
    Long s14;
    private @Column(name = "19")
    Long s15;
    private @Column(name = "20")
    Long s16;
    private @Column(name = "21")
    Long s17;
    private @Column(name = "22")
    Long s18;
    private @Column(name = "23")
    Long s19;
    private @Column(name = "24")
    Long s20;
    private @Column(name = "25")
    Long s21;
    private @Column(name = "device", tag = true)
    String device;

    public TurbofanModel() {

    }

    public TurbofanModel(Instant time,
                         Long cycle,
                         Long setting1,
                         Long setting2,
                         Long setting3,
                         Long s1,
                         Long s2,
                         Long s3,
                         Long s4,
                         Long s5,
                         Long s6,
                         Long s7,
                         Long s8,
                         Long s9,
                         Long s10,
                         Long s11,
                         Long s12,
                         Long s13,
                         Long s14,
                         Long s15,
                         Long s16,
                         Long s17,
                         Long s18,
                         Long s19,
                         Long s20,
                         Long s21,
                         String device) {
        this();
        this.time = time;
        this.device = device;
        this.cycle = cycle;
        this.setting1 = setting1;
        this.setting2 = setting2;
        this.setting3 = setting3;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
        this.s10 = s10;
        this.s11 = s11;
        this.s12 = s12;
        this.s13 = s13;
        this.s14 = s14;
        this.s15 = s15;
        this.s16 = s16;
        this.s17 = s17;
        this.s18 = s18;
        this.s19 = s19;
        this.s20 = s20;
        this.s21 = s21;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Long getCycle() {
        return cycle;
    }

    public void setCycle(Long cycle) {
        this.cycle = cycle;
    }

    public Long getSetting1() {
        return setting1;
    }

    public void setSetting1(Long setting1) {
        this.setting1 = setting1;
    }

    public Long getSetting2() {
        return setting2;
    }

    public void setSetting2(Long setting2) {
        this.setting2 = setting2;
    }

    public Long getSetting3() {
        return setting3;
    }

    public void setSetting3(Long setting3) {
        this.setting3 = setting3;
    }

    public Long getS1() {
        return s1;
    }

    public void setS1(Long s1) {
        this.s1 = s1;
    }

    public Long getS2() {
        return s2;
    }

    public void setS2(Long s2) {
        this.s2 = s2;
    }

    public Long getS3() {
        return s3;
    }

    public void setS3(Long s3) {
        this.s3 = s3;
    }

    public Long getS4() {
        return s4;
    }

    public void setS4(Long s4) {
        this.s4 = s4;
    }

    public Long getS5() {
        return s5;
    }

    public void setS5(Long s5) {
        this.s5 = s5;
    }

    public Long getS6() {
        return s6;
    }

    public void setS6(Long s6) {
        this.s6 = s6;
    }

    public Long getS7() {
        return s7;
    }

    public void setS7(Long s7) {
        this.s7 = s7;
    }

    public Long getS8() {
        return s8;
    }

    public void setS8(Long s8) {
        this.s8 = s8;
    }

    public Long getS9() {
        return s9;
    }

    public void setS9(Long s9) {
        this.s9 = s9;
    }

    public Long getS10() {
        return s10;
    }

    public void setS10(Long s10) {
        this.s10 = s10;
    }

    public Long getS11() {
        return s11;
    }

    public void setS11(Long s11) {
        this.s11 = s11;
    }

    public Long getS12() {
        return s12;
    }

    public void setS12(Long s12) {
        this.s12 = s12;
    }

    public Long getS13() {
        return s13;
    }

    public void setS13(Long s13) {
        this.s13 = s13;
    }

    public Long getS14() {
        return s14;
    }

    public void setS14(Long s14) {
        this.s14 = s14;
    }

    public Long getS15() {
        return s15;
    }

    public void setS15(Long s15) {
        this.s15 = s15;
    }

    public Long getS16() {
        return s16;
    }

    public void setS16(Long s16) {
        this.s16 = s16;
    }

    public Long getS17() {
        return s17;
    }

    public void setS17(Long s17) {
        this.s17 = s17;
    }

    public Long getS18() {
        return s18;
    }

    public void setS18(Long s18) {
        this.s18 = s18;
    }

    public Long getS19() {
        return s19;
    }

    public void setS19(Long s19) {
        this.s19 = s19;
    }

    public Long getS20() {
        return s20;
    }

    public void setS20(Long s20) {
        this.s20 = s20;
    }

    public Long getS21() {
        return s21;
    }

    public void setS21(Long s21) {
        this.s21 = s21;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
