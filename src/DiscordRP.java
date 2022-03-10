import club.minnced.discord.rpc.*;

public class DiscordRP {
    private DiscordRPC lib;
    private DiscordEventHandlers handlers;
    private String applicationId;
    private String steamId;
    private DiscordRichPresence presence;

    public DiscordRP() {
        this.lib = DiscordRPC.INSTANCE;
        this.applicationId = "951391829847310336";
        this.steamId = "";

        this.handlers = new DiscordEventHandlers();

        this.handlers.ready = (user) -> System.out.println("Ready!");

        this.lib.Discord_Initialize(this.applicationId, this.handlers, true, this.steamId);

        this.presence = new DiscordRichPresence();
        this.presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second

        this.presence.details = "Testing RPC";
        this.lib.Discord_UpdatePresence(presence);

        this.start();
    }

    private void start() {
         // in a worker thread
         new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
    }

    public void updateDetails(String details) {
        this.presence.details = details;
        this.lib.Discord_UpdatePresence(presence);
    }
}
