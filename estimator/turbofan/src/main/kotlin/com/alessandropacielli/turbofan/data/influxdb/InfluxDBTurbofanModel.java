package com.alessandropacielli.turbofan.data.influxdb;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Measurement(name = "normalized", database = "test")
public class InfluxDBTurbofanModel implements Serializable {
    private @Column(name = "time")
    Instant time;
    private @Column(name = "cycle")
    double cycle;
    private @Column(name = "setting1")
    double setting1;
    private @Column(name = "setting2")
    double setting2;
    private @Column(name = "setting3")
    double setting3;
    private @Column(name = "s1")
    double s1;
    private @Column(name = "s2")
    double s2;
    private @Column(name = "s3")
    double s3;
    private @Column(name = "s4")
    double s4;
    private @Column(name = "s5")
    double s5;
    private @Column(name = "s6")
    double s6;
    private @Column(name = "s7")
    double s7;
    private @Column(name = "s8")
    double s8;
    private @Column(name = "s9")
    double s9;
    private @Column(name = "s10")
    double s10;
    private @Column(name = "s11")
    double s11;
    private @Column(name = "s12")
    double s12;
    private @Column(name = "s13")
    double s13;
    private @Column(name = "s14")
    double s14;
    private @Column(name = "s15")
    double s15;
    private @Column(name = "s16")
    double s16;
    private @Column(name = "s17")
    double s17;
    private @Column(name = "s18")
    double s18;
    private @Column(name = "s19")
    double s19;
    private @Column(name = "s20")
    double s20;
    private @Column(name = "s21")
    double s21;
    private @Column(name = "device", tag = true)
    String device;

    public InfluxDBTurbofanModel() {

    }

    public InfluxDBTurbofanModel(Instant time,
                                 double cycle,
                                 double setting1,
                                 double setting2,
                                 double setting3,
                                 double s1,
                                 double s2,
                                 double s3,
                                 double s4,
                                 double s5,
                                 double s6,
                                 double s7,
                                 double s8,
                                 double s9,
                                 double s10,
                                 double s11,
                                 double s12,
                                 double s13,
                                 double s14,
                                 double s15,
                                 double s16,
                                 double s17,
                                 double s18,
                                 double s19,
                                 double s20,
                                 double s21,
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

    public double getCycle() {
        return cycle;
    }

    public void setCycle(double cycle) {
        this.cycle = cycle;
    }

    public double getSetting1() {
        return setting1;
    }

    public void setSetting1(double setting1) {
        this.setting1 = setting1;
    }

    public double getSetting2() {
        return setting2;
    }

    public void setSetting2(double setting2) {
        this.setting2 = setting2;
    }

    public double getSetting3() {
        return setting3;
    }

    public void setSetting3(double setting3) {
        this.setting3 = setting3;
    }

    public double getS1() {
        return s1;
    }

    public void setS1(double s1) {
        this.s1 = s1;
    }

    public double getS2() {
        return s2;
    }

    public void setS2(double s2) {
        this.s2 = s2;
    }

    public double getS3() {
        return s3;
    }

    public void setS3(double s3) {
        this.s3 = s3;
    }

    public double getS4() {
        return s4;
    }

    public void setS4(double s4) {
        this.s4 = s4;
    }

    public double getS5() {
        return s5;
    }

    public void setS5(double s5) {
        this.s5 = s5;
    }

    public double getS6() {
        return s6;
    }

    public void setS6(double s6) {
        this.s6 = s6;
    }

    public double getS7() {
        return s7;
    }

    public void setS7(double s7) {
        this.s7 = s7;
    }

    public double getS8() {
        return s8;
    }

    public void setS8(double s8) {
        this.s8 = s8;
    }

    public double getS9() {
        return s9;
    }

    public void setS9(double s9) {
        this.s9 = s9;
    }

    public double getS10() {
        return s10;
    }

    public void setS10(double s10) {
        this.s10 = s10;
    }

    public double getS11() {
        return s11;
    }

    public void setS11(double s11) {
        this.s11 = s11;
    }

    public double getS12() {
        return s12;
    }

    public void setS12(double s12) {
        this.s12 = s12;
    }

    public double getS13() {
        return s13;
    }

    public void setS13(double s13) {
        this.s13 = s13;
    }

    public double getS14() {
        return s14;
    }

    public void setS14(double s14) {
        this.s14 = s14;
    }

    public double getS15() {
        return s15;
    }

    public void setS15(double s15) {
        this.s15 = s15;
    }

    public double getS16() {
        return s16;
    }

    public void setS16(double s16) {
        this.s16 = s16;
    }

    public double getS17() {
        return s17;
    }

    public void setS17(double s17) {
        this.s17 = s17;
    }

    public double getS18() {
        return s18;
    }

    public void setS18(double s18) {
        this.s18 = s18;
    }

    public double getS19() {
        return s19;
    }

    public void setS19(double s19) {
        this.s19 = s19;
    }

    public double getS20() {
        return s20;
    }

    public void setS20(double s20) {
        this.s20 = s20;
    }

    public double getS21() {
        return s21;
    }

    public void setS21(double s21) {
        this.s21 = s21;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "InfluxDBTurbofanModel{" +
                "time=" + time +
                ", cycle=" + cycle +
                ", setting1=" + setting1 +
                ", setting2=" + setting2 +
                ", setting3=" + setting3 +
                ", s1=" + s1 +
                ", s2=" + s2 +
                ", s3=" + s3 +
                ", s4=" + s4 +
                ", s5=" + s5 +
                ", s6=" + s6 +
                ", s7=" + s7 +
                ", s8=" + s8 +
                ", s9=" + s9 +
                ", s10=" + s10 +
                ", s11=" + s11 +
                ", s12=" + s12 +
                ", s13=" + s13 +
                ", s14=" + s14 +
                ", s15=" + s15 +
                ", s16=" + s16 +
                ", s17=" + s17 +
                ", s18=" + s18 +
                ", s19=" + s19 +
                ", s20=" + s20 +
                ", s21=" + s21 +
                ", device='" + device + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfluxDBTurbofanModel that = (InfluxDBTurbofanModel) o;
        return Double.compare(that.cycle, cycle) == 0 &&
                Double.compare(that.setting1, setting1) == 0 &&
                Double.compare(that.setting2, setting2) == 0 &&
                Double.compare(that.setting3, setting3) == 0 &&
                Double.compare(that.s1, s1) == 0 &&
                Double.compare(that.s2, s2) == 0 &&
                Double.compare(that.s3, s3) == 0 &&
                Double.compare(that.s4, s4) == 0 &&
                Double.compare(that.s5, s5) == 0 &&
                Double.compare(that.s6, s6) == 0 &&
                Double.compare(that.s7, s7) == 0 &&
                Double.compare(that.s8, s8) == 0 &&
                Double.compare(that.s9, s9) == 0 &&
                Double.compare(that.s10, s10) == 0 &&
                Double.compare(that.s11, s11) == 0 &&
                Double.compare(that.s12, s12) == 0 &&
                Double.compare(that.s13, s13) == 0 &&
                Double.compare(that.s14, s14) == 0 &&
                Double.compare(that.s15, s15) == 0 &&
                Double.compare(that.s16, s16) == 0 &&
                Double.compare(that.s17, s17) == 0 &&
                Double.compare(that.s18, s18) == 0 &&
                Double.compare(that.s19, s19) == 0 &&
                Double.compare(that.s20, s20) == 0 &&
                Double.compare(that.s21, s21) == 0 &&
                time.equals(that.time) &&
                device.equals(that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, cycle, setting1, setting2, setting3, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20, s21, device);
    }
}
