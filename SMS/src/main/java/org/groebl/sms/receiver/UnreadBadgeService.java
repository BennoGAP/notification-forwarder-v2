package org.groebl.sms.receiver;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import org.groebl.sms.transaction.SmsHelper;
import org.groebl.sms.ui.widget.WidgetProvider;
import me.leolin.shortcutbadger.ShortcutBadger;

public class UnreadBadgeService extends IntentService {

    public static final String UNREAD_COUNT_UPDATED = "org.groebl.sms.intent.action.UNREAD_COUNT_UPDATED";

    public UnreadBadgeService() {
        super("UnreadBadgeService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (UNREAD_COUNT_UPDATED.equals(intent.getAction())) {
            ShortcutBadger.applyCount(this, SmsHelper.getUnreadMessageCount(this));
            WidgetProvider.notifyDatasetChanged(this);
        }
    }

    public static void update(Context context) {
        Intent intent = new Intent(context, UnreadBadgeService.class);
        intent.setAction(UNREAD_COUNT_UPDATED);
        context.startService(intent);
    }
}
