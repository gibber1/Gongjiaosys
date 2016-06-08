package l_wl.cn.gongjiaosys.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by l_wl on 2016/6/5.
 */
public class news extends BmobObject implements Serializable {
    private String title;
    private String digest;
    private String body;
    private BmobFile img;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setImg(BmobFile img) {
        this.img = img;
    }

    public String getTitle() {

        return title;
    }

    public String getDigest() {
        return digest;
    }

    public String getBody() {
        return body;
    }

    public BmobFile getImg() {
        return img;
    }
}
