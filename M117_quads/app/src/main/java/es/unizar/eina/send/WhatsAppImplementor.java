package es.unizar.eina.send;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.content.Intent;
import android.widget.Toast;

/** Concrete implementor utilizando la aplicación de WhatsApp. No funciona en el emulador si no se ha configurado previamente */
public class WhatsAppImplementor implements SendImplementor{

    /** actividad desde la cual se abrirá la aplicación de WhatsApp */
    private Activity sourceActivity;

    /** Constructor
     * @param source actividad desde la cual se abrira la aplicación de Whatsapp
     */
    public WhatsAppImplementor(Activity source){
        setSourceActivity(source);
    }

    /**  Actualiza la actividad desde la cual se abrira la actividad de gestion de correo */
    public void setSourceActivity(Activity source) {
        sourceActivity = source;
    }

    /**  Recupera la actividad desde la cual se abrira la aplicación de Whatsapp */
    public Activity getSourceActivity(){
        return sourceActivity;
    }

    /**
     * Implementacion del metodo send utilizando la aplicacion de WhatsApp
     * @param phone teléfono
     * @param message cuerpo del mensaje
     */
    public void send(String phone, String message) {

        PackageManager pm = getSourceActivity().getPackageManager();
        boolean appInstalled;

        try {
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            appInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            appInstalled = false;
        }

        if (appInstalled) {

//            Uri smsUri = Uri.parse("sms:" + phone);
//            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
//            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
//            sendIntent.setPackage("com.whatsapp");
//            getSourceActivity().startActivity(sendIntent);

            String uri = "https://wa.me/" + phone + "?text=" + Uri.encode(message);
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse(uri));
            sendIntent.setPackage("com.whatsapp");
            getSourceActivity().startActivity(sendIntent);

        } else {
            Toast.makeText(
                    getSourceActivity(),
                    "WhatsApp no está instalado",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

}
