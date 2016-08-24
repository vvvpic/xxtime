package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/18.
 */
public class CitysBean  implements Serializable {

    /**
     * code : 110000
     * addName : 北京市
     * city : [{"area":[{"code":"110101","addName":"东城区"},{"code":"110102","addName":"西城区"},{"code":"110103","addName":"崇文区"},{"code":"110104","addName":"宣武区"},{"code":"110105","addName":"朝阳区"},{"code":"110106","addName":"丰台区"},{"code":"110107","addName":"石景山区"},{"code":"110108","addName":"海淀区"},{"code":"110109","addName":"门头沟区"},{"code":"110111","addName":"房山区"},{"code":"110112","addName":"通州区"},{"code":"110113","addName":"顺义区"},{"code":"110114","addName":"昌平区"},{"code":"110115","addName":"大兴区"},{"code":"110116","addName":"怀柔区"},{"code":"110117","addName":"平谷区"},{"code":"110228","addName":"密云县"},{"code":"110229","addName":"延庆县"},{"code":"110230","addName":"其它区"}],"code":"110100","addName":"北京市"}]
     */

    private List<ProvinceBean> province;

    public List<ProvinceBean> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceBean> province) {
        this.province = province;
    }

    public static class ProvinceBean extends AreaBean implements Serializable{

        /**
         * area : [{"code":"110101","addName":"东城区"},{"code":"110102","addName":"西城区"},{"code":"110103","addName":"崇文区"},{"code":"110104","addName":"宣武区"},{"code":"110105","addName":"朝阳区"},{"code":"110106","addName":"丰台区"},{"code":"110107","addName":"石景山区"},{"code":"110108","addName":"海淀区"},{"code":"110109","addName":"门头沟区"},{"code":"110111","addName":"房山区"},{"code":"110112","addName":"通州区"},{"code":"110113","addName":"顺义区"},{"code":"110114","addName":"昌平区"},{"code":"110115","addName":"大兴区"},{"code":"110116","addName":"怀柔区"},{"code":"110117","addName":"平谷区"},{"code":"110228","addName":"密云县"},{"code":"110229","addName":"延庆县"},{"code":"110230","addName":"其它区"}]
         * code : 110100
         * addName : 北京市
         */

        private List<CityBean> city;

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean extends AreaBean implements Serializable{

            /**
             * code : 110101
             * addName : 东城区
             */

            private List<AreaBean> area;



            public List<AreaBean> getArea() {
                return area;
            }

            public void setArea(List<AreaBean> area) {
                this.area = area;
            }


        }
    }
}
