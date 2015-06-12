package belows.com.tangshi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import belows.com.tangshi.domain.MingJu;
import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/12.
 */
public class FileUtil {
    public static String readAssetFile(String pFileName, String pCharsetName) throws IOException {
        InputStream _inputStream = AppModel.INSTANCE.context().getAssets().open(pFileName);
        BufferedReader _br = new BufferedReader(new InputStreamReader(_inputStream, pCharsetName));
        String _line = "";
        String _result = "";
        while ((_line = _br.readLine()) != null) {
            _result += _line;
        }
        return _result;
    }
}
