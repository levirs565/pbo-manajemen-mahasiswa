package id.alfonlevi.mahasiswa.data.model;

public class Periode {
    public static final String[] sSEMESTER = {"Ganjil", "Genap"};

    private String mId;
    private int mTahun;
    private boolean mIsGenap;

    public Periode(String id, int tahun, boolean isGenap) {
        mId = id;
        mTahun = tahun;
        mIsGenap = isGenap;
    }

    public String getId() {
        return mId;
    }

    public int getTahun() {
        return mTahun;
    }

    public boolean isGenap() {
        return mIsGenap;
    }

    public String getSemester() {
        return mIsGenap ? sSEMESTER[1] : sSEMESTER[0];
    }
}
