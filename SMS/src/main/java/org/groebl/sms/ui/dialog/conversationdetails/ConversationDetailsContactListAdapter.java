package org.groebl.sms.ui.dialog.conversationdetails;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import org.groebl.sms.R;
import org.groebl.sms.data.Contact;
import org.groebl.sms.data.ContactList;
import org.groebl.sms.ui.view.AvatarView;
import org.groebl.sms.ui.view.QKTextView;

public class ConversationDetailsContactListAdapter extends ArrayAdapter {

    private ContactList mContacts;

    public ConversationDetailsContactListAdapter(Context context, ContactList contacts) {
        super(context, R.layout.list_item_recipient);
        mContacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.list_item_recipient, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact contact = mContacts.get(position);

        holder.name.setText(contact.getName());
        holder.address.setText(contact.getNumber());
        holder.avatar.setImageDrawable(contact.getAvatar(getContext(), null));
        holder.avatar.setContactName(contact.getName());
        if (contact.existsInDatabase()) {
            holder.avatar.assignContactUri(contact.getUri());
        } else {
            holder.avatar.assignContactFromPhone(contact.getNumber(), true);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return mContacts.size();
    }

    static class ViewHolder {
        AvatarView avatar;
        QKTextView name;
        QKTextView address;

        public ViewHolder(View view) {
            avatar = (AvatarView) view.findViewById(R.id.avatar);
            name = (QKTextView) view.findViewById(R.id.name);
            address = (QKTextView) view.findViewById(R.id.address);
        }
    }
}
