package com.outsourcebooster.cryptocurrency.common.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rklimemnko on 26.05.2016.
 */
@Document
public class Value {
    @Id
    private String id;

    private Currency btc;
    private Currency eth;
    private Currency xrp;
    private Currency ltc;
    private Currency dash;
    private Currency maid;
    private Currency doge;
    private Currency bts;
    private Currency xem;
    private Currency xmr;
    private Currency fct;
    private Currency emc;
    private Currency amp;
    private Currency xlm;
    private Currency ppc;
    private Currency tips;
    private Currency bcn;
    private Currency nxt;
    private Currency nmc;
    private Currency agrs;
    private Currency ybc;
    private Currency sjcx;
    private Currency xcp;
    private Currency grc;
    private Currency rby;
    private Currency sys;
    private Currency rbt;
    private Currency bcy;
    private Currency vpn;
    private Currency game;
    private Currency dgb;
    private Currency nsr;
    private Currency xvg;
    private Currency blk;
    private Currency mona;
    private Currency dcr;
    private Currency muse;
    private Currency scot;
    private Currency sec;
    private Currency slr;
    private Currency mint;
    private Currency start;
    private Currency aur;
    private Currency xau;
    private Currency rads;
    private Currency usdt;
    private Currency creva;
    private Currency btcd;
    private Currency xvc;
    private Currency vrc;
    private Currency vtc;
    private Currency eac;
    private Currency clam;
    private Currency sc;
    private Currency ioc;
    private Currency xpm;
    private Currency fc2;
    private Currency unity;
    private Currency ftc;
    private Currency rdd;
    private Currency nvc;
    private Currency qora;
    private Currency gemz;
    private Currency qrk;
    private Currency exp;
    private Currency omni;
    private Currency neu;
    private Currency moon;
    private Currency ifc;
    private Currency usnbt;
    private Currency sdc;
    private Currency xdn;
    private Currency nlg;
    private Currency wdc;
    private Currency note;
    private Currency cure;
    private Currency swarm;
    private Currency obits;
    private Currency gcr;
    private Currency xcr;
    private Currency bost;
    private Currency amber;
    private Currency sib;
    private Currency xsi;
    private Currency mec;
    private Currency cloak;
    private Currency hmp;
    private Currency bils;
    private Currency dmd;
    private Currency zcc;
    private Currency dgc;
    private Currency zet;
    private Currency diem;
    private Currency unc;
    private Currency uno;
    private Currency bay;
    private Currency csc;
    private Currency apc;
    private Currency swift;
    private Currency anc;
    private Currency burst;
    private Currency wbb;
    private Currency zeit;
    private Currency fair;
    private Currency ixc;
    private Currency coino;
    private Currency yoc;
    private Currency dex;
    private Currency cv2;
    private Currency circ;
    private Currency rbr;
    private Currency block;
    private Currency nav;
    private Currency i0c;
    private Currency frc;
    private Currency pot;
    private Currency pls;
    private Currency bks;
    private Currency vox;
    private Currency lqd;
    private Currency fimk;
    private Currency cpc;
    private Currency neos;
    private Currency vta;
    private Currency ac;
    private Currency adcn;
    private Currency steem;
    private Currency pts;
    private Currency flav;
    private Currency crbit;
    private Currency smc;
    private Currency jinn;
    private Currency sub;
    private Currency dgd;
    private Currency pkb;
    private Currency exe;
    private Currency xpb;
    private Currency xbc;
    private Currency duo;
    private Currency niro;
    private Currency dbic;
    //private Currency 2give;
    private Currency klc;
    private Currency el;
    private Currency tix;
    private Currency lsk;
    private Currency dao;
    private Currency glc;
    private Currency silk;
    private Currency cnmt;
    private Currency spots;
    private Currency xhi;
    private Currency fly;
    private Currency gld;
    private Currency kobo;
    private Currency ion;
    private Currency mnm;
    //private Currency 8bit;
    private Currency htc;
    private Currency naut;
    private Currency jlh;
    private Currency tes;
    private Currency ric;
    private Currency skynet;
    private Currency sls;
    private Currency waves;
    private Currency fund;
    private Currency adz;
    private Currency cyt;
    private Currency flo;
    private Currency lxc;
    private Currency voot;
    private Currency nxtv;
    private Currency jack;
    private Currency rise;
    private Currency trump;
    private Currency xbs;
    private Currency emc2;
    private Currency myr;
    private Currency hz;
    private Currency unb;
    private Currency putin;
    private Currency mmnxt;
    private Currency brk;
    private Currency ree;
    private Currency lir;
    private Currency via;
    private Currency btm;
    private Currency lbc;
    private Currency onec;
    private Currency trust;
    private Currency synx;
    private Currency cj;
    private Currency sar;
    private Currency xc;
    private Currency erc;
    private Currency etc;
    private Currency xst;
    private Currency au;
    private Currency egc;
    private Currency brx;
    private Currency spr;
    //private Currency 007;
    private Currency scn;
    private Currency adc;
    private Currency mue;
    private Currency xcn;
    private Currency kr;
    private Currency fibre;
    private Currency gam;
    private Currency sts;
    private Currency strat;
    private Currency peerplays;
    private Currency pdc;
    private Currency arb;
    private Currency dnet;
    private Currency coval;
    private Currency xaur;

    @Indexed
    private LocalDateTime date;

    public Value() {
        date = LocalDateTime.now();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Currency getBtc() {
        return btc;
    }

    public void setBtc(Currency btc) {
        this.btc = btc;
    }

    public Currency getEth() {
        return eth;
    }

    public void setEth(Currency eth) {
        this.eth = eth;
    }

    public Timestamp getDate() {
        return Timestamp.valueOf(date);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Currency getLtc() {
        return ltc;
    }

    public void setLtc(Currency ltc) {
        this.ltc = ltc;
    }

    public Currency getDoge() {
        return doge;
    }

    public void setDoge(Currency doge) {
        this.doge = doge;
    }

    public Currency getXrp() {
        return xrp;
    }

    public void setXrp(Currency xrp) {
        this.xrp = xrp;
    }

    public Currency getDash() {
        return dash;
    }

    public void setDash(Currency dash) {
        this.dash = dash;
    }

    public Currency getMaid() {
        return maid;
    }

    public void setMaid(Currency maid) {
        this.maid = maid;
    }

    public Currency getBts() {
        return bts;
    }

    public void setBts(Currency bts) {
        this.bts = bts;
    }

    public Currency getXem() {
        return xem;
    }

    public void setXem(Currency xem) {
        this.xem = xem;
    }

    public Currency getXmr() {
        return xmr;
    }

    public void setXmr(Currency xmr) {
        this.xmr = xmr;
    }

    public Currency getFct() {
        return fct;
    }

    public void setFct(Currency fct) {
        this.fct = fct;
    }

    public Currency getEmc() {
        return emc;
    }

    public void setEmc(Currency emc) {
        this.emc = emc;
    }

    public Currency getAmp() {
        return amp;
    }

    public void setAmp(Currency amp) {
        this.amp = amp;
    }

    public Currency getXlm() {
        return xlm;
    }

    public void setXlm(Currency xlm) {
        this.xlm = xlm;
    }

    public Currency getPpc() {
        return ppc;
    }

    public void setPpc(Currency ppc) {
        this.ppc = ppc;
    }

    public Currency getTips() {
        return tips;
    }

    public void setTips(Currency tips) {
        this.tips = tips;
    }

    public Currency getBcn() {
        return bcn;
    }

    public void setBcn(Currency bcn) {
        this.bcn = bcn;
    }

    public Currency getNxt() {
        return nxt;
    }

    public void setNxt(Currency nxt) {
        this.nxt = nxt;
    }

    public Currency getNmc() {
        return nmc;
    }

    public void setNmc(Currency nmc) {
        this.nmc = nmc;
    }

    public Currency getAgrs() {
        return agrs;
    }

    public void setAgrs(Currency agrs) {
        this.agrs = agrs;
    }

    public Currency getYbc() {
        return ybc;
    }

    public void setYbc(Currency ybc) {
        this.ybc = ybc;
    }

    public Currency getSjcx() {
        return sjcx;
    }

    public void setSjcx(Currency sjcx) {
        this.sjcx = sjcx;
    }

    public Currency getXcp() {
        return xcp;
    }

    public void setXcp(Currency xcp) {
        this.xcp = xcp;
    }

    public Currency getGrc() {
        return grc;
    }

    public void setGrc(Currency grc) {
        this.grc = grc;
    }

    public Currency getRby() {
        return rby;
    }

    public void setRby(Currency rby) {
        this.rby = rby;
    }

    public Currency getSys() {
        return sys;
    }

    public void setSys(Currency sys) {
        this.sys = sys;
    }

    public Currency getRbt() {
        return rbt;
    }

    public void setRbt(Currency rbt) {
        this.rbt = rbt;
    }

    public Currency getBcy() {
        return bcy;
    }

    public void setBcy(Currency bcy) {
        this.bcy = bcy;
    }

    public Currency getVpn() {
        return vpn;
    }

    public void setVpn(Currency vpn) {
        this.vpn = vpn;
    }

    public Currency getGame() {
        return game;
    }

    public void setGame(Currency game) {
        this.game = game;
    }

    public Currency getDgb() {
        return dgb;
    }

    public void setDgb(Currency dgb) {
        this.dgb = dgb;
    }

    public Currency getNsr() {
        return nsr;
    }

    public void setNsr(Currency nsr) {
        this.nsr = nsr;
    }

    public Currency getXvg() {
        return xvg;
    }

    public void setXvg(Currency xvg) {
        this.xvg = xvg;
    }

    public Currency getBlk() {
        return blk;
    }

    public void setBlk(Currency blk) {
        this.blk = blk;
    }

    public Currency getMona() {
        return mona;
    }

    public void setMona(Currency mona) {
        this.mona = mona;
    }

    public Currency getDcr() {
        return dcr;
    }

    public void setDcr(Currency dcr) {
        this.dcr = dcr;
    }

    public Currency getMuse() {
        return muse;
    }

    public void setMuse(Currency muse) {
        this.muse = muse;
    }

    public Currency getScot() {
        return scot;
    }

    public void setScot(Currency scot) {
        this.scot = scot;
    }

    public Currency getSec() {
        return sec;
    }

    public void setSec(Currency sec) {
        this.sec = sec;
    }

    public Currency getSlr() {
        return slr;
    }

    public void setSlr(Currency slr) {
        this.slr = slr;
    }

    public Currency getMint() {
        return mint;
    }

    public void setMint(Currency mint) {
        this.mint = mint;
    }

    public Currency getStart() {
        return start;
    }

    public void setStart(Currency start) {
        this.start = start;
    }

    public Currency getAur() {
        return aur;
    }

    public void setAur(Currency aur) {
        this.aur = aur;
    }

    public Currency getXau() {
        return xau;
    }

    public void setXau(Currency xau) {
        this.xau = xau;
    }

    public Currency getRads() {
        return rads;
    }

    public void setRads(Currency rads) {
        this.rads = rads;
    }

    public Currency getUsdt() {
        return usdt;
    }

    public void setUsdt(Currency usdt) {
        this.usdt = usdt;
    }

    public Currency getCreva() {
        return creva;
    }

    public void setCreva(Currency creva) {
        this.creva = creva;
    }

    public Currency getBtcd() {
        return btcd;
    }

    public void setBtcd(Currency btcd) {
        this.btcd = btcd;
    }

    public Currency getXvc() {
        return xvc;
    }

    public void setXvc(Currency xvc) {
        this.xvc = xvc;
    }

    public Currency getVrc() {
        return vrc;
    }

    public void setVrc(Currency vrc) {
        this.vrc = vrc;
    }

    public Currency getVtc() {
        return vtc;
    }

    public void setVtc(Currency vtc) {
        this.vtc = vtc;
    }

    public Currency getEac() {
        return eac;
    }

    public void setEac(Currency eac) {
        this.eac = eac;
    }

    public Currency getClam() {
        return clam;
    }

    public void setClam(Currency clam) {
        this.clam = clam;
    }

    public Currency getSc() {
        return sc;
    }

    public void setSc(Currency sc) {
        this.sc = sc;
    }

    public Currency getIoc() {
        return ioc;
    }

    public void setIoc(Currency ioc) {
        this.ioc = ioc;
    }

    public Currency getXpm() {
        return xpm;
    }

    public void setXpm(Currency xpm) {
        this.xpm = xpm;
    }

    public Currency getFc2() {
        return fc2;
    }

    public void setFc2(Currency fc2) {
        this.fc2 = fc2;
    }

    public Currency getUnity() {
        return unity;
    }

    public void setUnity(Currency unity) {
        this.unity = unity;
    }

    public Currency getFtc() {
        return ftc;
    }

    public void setFtc(Currency ftc) {
        this.ftc = ftc;
    }

    public Currency getRdd() {
        return rdd;
    }

    public void setRdd(Currency rdd) {
        this.rdd = rdd;
    }

    public Currency getNvc() {
        return nvc;
    }

    public void setNvc(Currency nvc) {
        this.nvc = nvc;
    }

    public Currency getQora() {
        return qora;
    }

    public void setQora(Currency qora) {
        this.qora = qora;
    }

    public Currency getGemz() {
        return gemz;
    }

    public void setGemz(Currency gemz) {
        this.gemz = gemz;
    }

    public Currency getQrk() {
        return qrk;
    }

    public void setQrk(Currency qrk) {
        this.qrk = qrk;
    }

    public Currency getExp() {
        return exp;
    }

    public void setExp(Currency exp) {
        this.exp = exp;
    }

    public Currency getOmni() {
        return omni;
    }

    public void setOmni(Currency omni) {
        this.omni = omni;
    }

    public Currency getNeu() {
        return neu;
    }

    public void setNeu(Currency neu) {
        this.neu = neu;
    }

    public Currency getMoon() {
        return moon;
    }

    public void setMoon(Currency moon) {
        this.moon = moon;
    }

    public Currency getIfc() {
        return ifc;
    }

    public void setIfc(Currency ifc) {
        this.ifc = ifc;
    }

    public Currency getUsnbt() {
        return usnbt;
    }

    public void setUsnbt(Currency usnbt) {
        this.usnbt = usnbt;
    }

    public Currency getSdc() {
        return sdc;
    }

    public void setSdc(Currency sdc) {
        this.sdc = sdc;
    }

    public Currency getXdn() {
        return xdn;
    }

    public void setXdn(Currency xdn) {
        this.xdn = xdn;
    }

    public Currency getNlg() {
        return nlg;
    }

    public void setNlg(Currency nlg) {
        this.nlg = nlg;
    }

    public Currency getWdc() {
        return wdc;
    }

    public void setWdc(Currency wdc) {
        this.wdc = wdc;
    }

    public Currency getNote() {
        return note;
    }

    public void setNote(Currency note) {
        this.note = note;
    }

    public Currency getCure() {
        return cure;
    }

    public void setCure(Currency cure) {
        this.cure = cure;
    }

    public Currency getSwarm() {
        return swarm;
    }

    public void setSwarm(Currency swarm) {
        this.swarm = swarm;
    }

    public Currency getObits() {
        return obits;
    }

    public void setObits(Currency obits) {
        this.obits = obits;
    }

    public Currency getGcr() {
        return gcr;
    }

    public void setGcr(Currency gcr) {
        this.gcr = gcr;
    }

    public Currency getXcr() {
        return xcr;
    }

    public void setXcr(Currency xcr) {
        this.xcr = xcr;
    }

    public Currency getBost() {
        return bost;
    }

    public void setBost(Currency bost) {
        this.bost = bost;
    }

    public Currency getAmber() {
        return amber;
    }

    public void setAmber(Currency amber) {
        this.amber = amber;
    }

    public Currency getSib() {
        return sib;
    }

    public void setSib(Currency sib) {
        this.sib = sib;
    }

    public Currency getXsi() {
        return xsi;
    }

    public void setXsi(Currency xsi) {
        this.xsi = xsi;
    }

    public Currency getMec() {
        return mec;
    }

    public void setMec(Currency mec) {
        this.mec = mec;
    }

    public Currency getCloak() {
        return cloak;
    }

    public void setCloak(Currency cloak) {
        this.cloak = cloak;
    }

    public Currency getHmp() {
        return hmp;
    }

    public void setHmp(Currency hmp) {
        this.hmp = hmp;
    }

    public Currency getBils() {
        return bils;
    }

    public void setBils(Currency bils) {
        this.bils = bils;
    }

    public Currency getDmd() {
        return dmd;
    }

    public void setDmd(Currency dmd) {
        this.dmd = dmd;
    }

    public Currency getZcc() {
        return zcc;
    }

    public void setZcc(Currency zcc) {
        this.zcc = zcc;
    }

    public Currency getDgc() {
        return dgc;
    }

    public void setDgc(Currency dgc) {
        this.dgc = dgc;
    }

    public Currency getZet() {
        return zet;
    }

    public void setZet(Currency zet) {
        this.zet = zet;
    }

    public Currency getDiem() {
        return diem;
    }

    public void setDiem(Currency diem) {
        this.diem = diem;
    }

    public Currency getUnc() {
        return unc;
    }

    public void setUnc(Currency unc) {
        this.unc = unc;
    }

    public Currency getUno() {
        return uno;
    }

    public void setUno(Currency uno) {
        this.uno = uno;
    }

    public Currency getBay() {
        return bay;
    }

    public void setBay(Currency bay) {
        this.bay = bay;
    }

    public Currency getCsc() {
        return csc;
    }

    public void setCsc(Currency csc) {
        this.csc = csc;
    }

    public Currency getApc() {
        return apc;
    }

    public void setApc(Currency apc) {
        this.apc = apc;
    }

    public Currency getSwift() {
        return swift;
    }

    public void setSwift(Currency swift) {
        this.swift = swift;
    }

    public Currency getAnc() {
        return anc;
    }

    public void setAnc(Currency anc) {
        this.anc = anc;
    }

    public Currency getBurst() {
        return burst;
    }

    public void setBurst(Currency burst) {
        this.burst = burst;
    }

    public Currency getWbb() {
        return wbb;
    }

    public void setWbb(Currency wbb) {
        this.wbb = wbb;
    }

    public Currency getZeit() {
        return zeit;
    }

    public void setZeit(Currency zeit) {
        this.zeit = zeit;
    }

    public Currency getFair() {
        return fair;
    }

    public void setFair(Currency fair) {
        this.fair = fair;
    }

    public Currency getIxc() {
        return ixc;
    }

    public void setIxc(Currency ixc) {
        this.ixc = ixc;
    }

    public Currency getCoino() {
        return coino;
    }

    public void setCoino(Currency coino) {
        this.coino = coino;
    }

    public Currency getYoc() {
        return yoc;
    }

    public void setYoc(Currency yoc) {
        this.yoc = yoc;
    }

    public Currency getDex() {
        return dex;
    }

    public void setDex(Currency dex) {
        this.dex = dex;
    }

    public Currency getCv2() {
        return cv2;
    }

    public void setCv2(Currency cv2) {
        this.cv2 = cv2;
    }

    public Currency getCirc() {
        return circ;
    }

    public void setCirc(Currency circ) {
        this.circ = circ;
    }

    public Currency getRbr() {
        return rbr;
    }

    public void setRbr(Currency rbr) {
        this.rbr = rbr;
    }

    public Currency getBlock() {
        return block;
    }

    public void setBlock(Currency block) {
        this.block = block;
    }

    public Currency getNav() {
        return nav;
    }

    public void setNav(Currency nav) {
        this.nav = nav;
    }

    public Currency getI0c() {
        return i0c;
    }

    public void setI0c(Currency i0c) {
        this.i0c = i0c;
    }

    public Currency getFrc() {
        return frc;
    }

    public void setFrc(Currency frc) {
        this.frc = frc;
    }

    public Currency getPot() {
        return pot;
    }

    public void setPot(Currency pot) {
        this.pot = pot;
    }

    public Currency getPls() {
        return pls;
    }

    public void setPls(Currency pls) {
        this.pls = pls;
    }

    public Currency getBks() {
        return bks;
    }

    public void setBks(Currency bks) {
        this.bks = bks;
    }

    public Currency getLqd() {
        return lqd;
    }

    public void setLqd(Currency lqd) {
        this.lqd = lqd;
    }

    public Currency getFimk() {
        return fimk;
    }

    public void setFimk(Currency fimk) {
        this.fimk = fimk;
    }

    public Currency getCpc() {
        return cpc;
    }

    public void setCpc(Currency cpc) {
        this.cpc = cpc;
    }

    public Currency getNeos() {
        return neos;
    }

    public void setNeos(Currency neos) {
        this.neos = neos;
    }

    public Currency getVta() {
        return vta;
    }

    public void setVta(Currency vta) {
        this.vta = vta;
    }

    public Currency getAc() {
        return ac;
    }

    public void setAc(Currency ac) {
        this.ac = ac;
    }

    public Currency getAdcn() {
        return adcn;
    }

    public void setAdcn(Currency adcn) {
        this.adcn = adcn;
    }

    public Currency getSteem() {
        return steem;
    }

    public void setSteem(Currency steem) {
        this.steem = steem;
    }

    public Currency getPts() {
        return pts;
    }

    public void setPts(Currency pts) {
        this.pts = pts;
    }

    public Currency getFlav() {
        return flav;
    }

    public void setFlav(Currency flav) {
        this.flav = flav;
    }

    public Currency getCrbit() {
        return crbit;
    }

    public void setCrbit(Currency crbit) {
        this.crbit = crbit;
    }

    public Currency getSmc() {
        return smc;
    }

    public void setSmc(Currency smc) {
        this.smc = smc;
    }

    public Currency getJinn() {
        return jinn;
    }

    public void setJinn(Currency jinn) {
        this.jinn = jinn;
    }

    public Currency getSub() {
        return sub;
    }

    public void setSub(Currency sub) {
        this.sub = sub;
    }

    public Currency getDgd() {
        return dgd;
    }

    public void setDgd(Currency dgd) {
        this.dgd = dgd;
    }

    public Currency getPkb() {
        return pkb;
    }

    public void setPkb(Currency pkb) {
        this.pkb = pkb;
    }

    public Currency getExe() {
        return exe;
    }

    public void setExe(Currency exe) {
        this.exe = exe;
    }

    public Currency getXpb() {
        return xpb;
    }

    public void setXpb(Currency xpb) {
        this.xpb = xpb;
    }

    public Currency getXbc() {
        return xbc;
    }

    public void setXbc(Currency xbc) {
        this.xbc = xbc;
    }

    public Currency getDuo() {
        return duo;
    }

    public void setDuo(Currency duo) {
        this.duo = duo;
    }

    public Currency getNiro() {
        return niro;
    }

    public void setNiro(Currency niro) {
        this.niro = niro;
    }

    public Currency getDbic() {
        return dbic;
    }

    public void setDbic(Currency dbic) {
        this.dbic = dbic;
    }

    public Currency getKlc() {
        return klc;
    }

    public void setKlc(Currency klc) {
        this.klc = klc;
    }

    public Currency getEl() {
        return el;
    }

    public void setEl(Currency el) {
        this.el = el;
    }

    public Currency getTix() {
        return tix;
    }

    public void setTix(Currency tix) {
        this.tix = tix;
    }

    public Currency getLsk() {
        return lsk;
    }

    public void setLsk(Currency lsk) {
        this.lsk = lsk;
    }

    public Currency getDao() {
        return dao;
    }

    public void setDao(Currency dao) {
        this.dao = dao;
    }

    public Currency getGlc() {
        return glc;
    }

    public void setGlc(Currency glc) {
        this.glc = glc;
    }

    public Currency getSilk() {
        return silk;
    }

    public void setSilk(Currency silk) {
        this.silk = silk;
    }

    public Currency getCnmt() {
        return cnmt;
    }

    public void setCnmt(Currency cnmt) {
        this.cnmt = cnmt;
    }

    public Currency getSpots() {
        return spots;
    }

    public void setSpots(Currency spots) {
        this.spots = spots;
    }

    public Currency getXhi() {
        return xhi;
    }

    public void setXhi(Currency xhi) {
        this.xhi = xhi;
    }

    public Currency getFly() {
        return fly;
    }

    public void setFly(Currency fly) {
        this.fly = fly;
    }

    public Currency getGld() {
        return gld;
    }

    public void setGld(Currency gld) {
        this.gld = gld;
    }

    public Currency getKobo() {
        return kobo;
    }

    public void setKobo(Currency kobo) {
        this.kobo = kobo;
    }

    public Currency getIon() {
        return ion;
    }

    public void setIon(Currency ion) {
        this.ion = ion;
    }

    public Currency getMnm() {
        return mnm;
    }

    public void setMnm(Currency mnm) {
        this.mnm = mnm;
    }

    public Currency getHtc() {
        return htc;
    }

    public void setHtc(Currency htc) {
        this.htc = htc;
    }

    public Currency getNaut() {
        return naut;
    }

    public void setNaut(Currency naut) {
        this.naut = naut;
    }

    public Currency getJlh() {
        return jlh;
    }

    public void setJlh(Currency jlh) {
        this.jlh = jlh;
    }

    public Currency getTes() {
        return tes;
    }

    public void setTes(Currency tes) {
        this.tes = tes;
    }

    public Currency getVox() {
        return vox;
    }

    public void setVox(Currency vox) {
        this.vox = vox;
    }

    public Currency getRic() {
        return ric;
    }

    public void setRic(Currency ric) {
        this.ric = ric;
    }

    public Currency getSkynet() {
        return skynet;
    }

    public void setSkynet(Currency skynet) {
        this.skynet = skynet;
    }

    public Currency getSls() {
        return sls;
    }

    public void setSls(Currency sls) {
        this.sls = sls;
    }

    public Currency getWaves() {
        return waves;
    }

    public void setWaves(Currency waves) {
        this.waves = waves;
    }

    public Currency getFund() {
        return fund;
    }

    public void setFund(Currency fund) {
        this.fund = fund;
    }

    public Currency getAdz() {
        return adz;
    }

    public void setAdz(Currency adz) {
        this.adz = adz;
    }

    public Currency getCyt() {
        return cyt;
    }

    public void setCyt(Currency cyt) {
        this.cyt = cyt;
    }

    public Currency getFlo() {
        return flo;
    }

    public void setFlo(Currency flo) {
        this.flo = flo;
    }

    public Currency getLxc() {
        return lxc;
    }

    public void setLxc(Currency lxc) {
        this.lxc = lxc;
    }

    public Currency getVoot() {
        return voot;
    }

    public void setVoot(Currency voot) {
        this.voot = voot;
    }

    public Currency getNxtv() {
        return nxtv;
    }

    public void setNxtv(Currency nxtv) {
        this.nxtv = nxtv;
    }

    public Currency getJack() {
        return jack;
    }

    public void setJack(Currency jack) {
        this.jack = jack;
    }

    public Currency getRise() {
        return rise;
    }

    public void setRise(Currency rise) {
        this.rise = rise;
    }

    public Currency getTrump() {
        return trump;
    }

    public void setTrump(Currency trump) {
        this.trump = trump;
    }

    public Currency getXbs() {
        return xbs;
    }

    public void setXbs(Currency xbs) {
        this.xbs = xbs;
    }

    public Currency getEmc2() {
        return emc2;
    }

    public void setEmc2(Currency emc2) {
        this.emc2 = emc2;
    }

    public Currency getMyr() {
        return myr;
    }

    public void setMyr(Currency myr) {
        this.myr = myr;
    }

    public Currency getHz() {
        return hz;
    }

    public void setHz(Currency hz) {
        this.hz = hz;
    }

    public Currency getUnb() {
        return unb;
    }

    public void setUnb(Currency unb) {
        this.unb = unb;
    }

    public Currency getPutin() {
        return putin;
    }

    public void setPutin(Currency putin) {
        this.putin = putin;
    }

    public Currency getMmnxt() {
        return mmnxt;
    }

    public void setMmnxt(Currency mmnxt) {
        this.mmnxt = mmnxt;
    }

    public Currency getBrk() {
        return brk;
    }

    public void setBrk(Currency brk) {
        this.brk = brk;
    }

    public Currency getRee() {
        return ree;
    }

    public void setRee(Currency ree) {
        this.ree = ree;
    }

    public Currency getLir() {
        return lir;
    }

    public void setLir(Currency lir) {
        this.lir = lir;
    }

    public Currency getVia() {
        return via;
    }

    public void setVia(Currency via) {
        this.via = via;
    }

    public Currency getBtm() {
        return btm;
    }

    public void setBtm(Currency btm) {
        this.btm = btm;
    }

    public Currency getLbc() {
        return lbc;
    }

    public void setLbc(Currency lbc) {
        this.lbc = lbc;
    }

    public Currency getOnec() {
        return onec;
    }

    public void setOnec(Currency onec) {
        this.onec = onec;
    }

    public Currency getTrust() {
        return trust;
    }

    public void setTrust(Currency trust) {
        this.trust = trust;
    }

    public Currency getSynx() {
        return synx;
    }

    public void setSynx(Currency synx) {
        this.synx = synx;
    }

    public Currency getCj() {
        return cj;
    }

    public void setCj(Currency cj) {
        this.cj = cj;
    }

    public Currency getSar() {
        return sar;
    }

    public void setSar(Currency sar) {
        this.sar = sar;
    }

    public Currency getXc() {
        return xc;
    }

    public void setXc(Currency xc) {
        this.xc = xc;
    }

    public Currency getErc() {
        return erc;
    }

    public void setErc(Currency erc) {
        this.erc = erc;
    }

    public Currency getEtc() {
        return etc;
    }

    public void setEtc(Currency etc) {
        this.etc = etc;
    }

    public Currency getXst() {
        return xst;
    }

    public void setXst(Currency xst) {
        this.xst = xst;
    }

    public Currency getAu() {
        return au;
    }

    public void setAu(Currency au) {
        this.au = au;
    }

    public Currency getEgc() {
        return egc;
    }

    public void setEgc(Currency egc) {
        this.egc = egc;
    }

    public Currency getBrx() {
        return brx;
    }

    public void setBrx(Currency brx) {
        this.brx = brx;
    }

    public Currency getSpr() {
        return spr;
    }

    public void setSpr(Currency spr) {
        this.spr = spr;
    }

    public Currency getScn() {
        return scn;
    }

    public void setScn(Currency scn) {
        this.scn = scn;
    }

    public Currency getAdc() {
        return adc;
    }

    public void setAdc(Currency adc) {
        this.adc = adc;
    }

    public Currency getMue() {
        return mue;
    }

    public void setMue(Currency mue) {
        this.mue = mue;
    }

    public Currency getXcn() {
        return xcn;
    }

    public void setXcn(Currency xcn) {
        this.xcn = xcn;
    }

    public Currency getKr() {
        return kr;
    }

    public void setKr(Currency kr) {
        this.kr = kr;
    }

    public Currency getFibre() {
        return fibre;
    }

    public void setFibre(Currency fibre) {
        this.fibre = fibre;
    }

    public Currency getGam() {
        return gam;
    }

    public void setGam(Currency gam) {
        this.gam = gam;
    }

    public Currency getSts() {
        return sts;
    }

    public void setSts(Currency sts) {
        this.sts = sts;
    }

    public Currency getStrat() {
        return strat;
    }

    public void setStrat(Currency strat) {
        this.strat = strat;
    }

    public Currency getPeerplays() {
        return peerplays;
    }

    public void setPeerplays(Currency peerplays) {
        this.peerplays = peerplays;
    }

    public Currency getPdc() {
        return pdc;
    }

    public void setPdc(Currency pdc) {
        this.pdc = pdc;
    }

    public Currency getArb() {
        return arb;
    }

    public void setArb(Currency arb) {
        this.arb = arb;
    }

    public Currency getDnet() {
        return dnet;
    }

    public void setDnet(Currency dnet) {
        this.dnet = dnet;
    }

    public Currency getCoval() {
        return coval;
    }

    public void setCoval(Currency coval) {
        this.coval = coval;
    }

    public Currency getXaur() {
        return xaur;
    }

    public void setXaur(Currency xaur) {
        this.xaur = xaur;
    }
}
