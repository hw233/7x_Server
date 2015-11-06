// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account.proto

package qxmobile.protobuf;

public final class AccountProtos {
  private AccountProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface AccountOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required string account = 1;
    /**
     * <code>required string account = 1;</code>
     */
    boolean hasAccount();
    /**
     * <code>required string account = 1;</code>
     */
    java.lang.String getAccount();
    /**
     * <code>required string account = 1;</code>
     */
    com.google.protobuf.ByteString
        getAccountBytes();

    // required int32 userId = 2;
    /**
     * <code>required int32 userId = 2;</code>
     */
    boolean hasUserId();
    /**
     * <code>required int32 userId = 2;</code>
     */
    int getUserId();

    // required int64 createDttm = 3;
    /**
     * <code>required int64 createDttm = 3;</code>
     */
    boolean hasCreateDttm();
    /**
     * <code>required int64 createDttm = 3;</code>
     */
    long getCreateDttm();

    // required int64 lastLoginDttm = 4;
    /**
     * <code>required int64 lastLoginDttm = 4;</code>
     */
    boolean hasLastLoginDttm();
    /**
     * <code>required int64 lastLoginDttm = 4;</code>
     */
    long getLastLoginDttm();
  }
  /**
   * Protobuf type {@code qxmobile.protobuf.Account}
   */
  public static final class Account extends
      com.google.protobuf.GeneratedMessage
      implements AccountOrBuilder {
    // Use Account.newBuilder() to construct.
    private Account(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Account(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Account defaultInstance;
    public static Account getDefaultInstance() {
      return defaultInstance;
    }

    public Account getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Account(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              account_ = input.readBytes();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              userId_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              createDttm_ = input.readInt64();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              lastLoginDttm_ = input.readInt64();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return qxmobile.protobuf.AccountProtos.internal_static_qxmobile_protobuf_Account_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return qxmobile.protobuf.AccountProtos.internal_static_qxmobile_protobuf_Account_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              qxmobile.protobuf.AccountProtos.Account.class, qxmobile.protobuf.AccountProtos.Account.Builder.class);
    }

    public static com.google.protobuf.Parser<Account> PARSER =
        new com.google.protobuf.AbstractParser<Account>() {
      public Account parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Account(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<Account> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required string account = 1;
    public static final int ACCOUNT_FIELD_NUMBER = 1;
    private java.lang.Object account_;
    /**
     * <code>required string account = 1;</code>
     */
    public boolean hasAccount() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string account = 1;</code>
     */
    public java.lang.String getAccount() {
      java.lang.Object ref = account_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          account_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string account = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAccountBytes() {
      java.lang.Object ref = account_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        account_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required int32 userId = 2;
    public static final int USERID_FIELD_NUMBER = 2;
    private int userId_;
    /**
     * <code>required int32 userId = 2;</code>
     */
    public boolean hasUserId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 userId = 2;</code>
     */
    public int getUserId() {
      return userId_;
    }

    // required int64 createDttm = 3;
    public static final int CREATEDTTM_FIELD_NUMBER = 3;
    private long createDttm_;
    /**
     * <code>required int64 createDttm = 3;</code>
     */
    public boolean hasCreateDttm() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int64 createDttm = 3;</code>
     */
    public long getCreateDttm() {
      return createDttm_;
    }

    // required int64 lastLoginDttm = 4;
    public static final int LASTLOGINDTTM_FIELD_NUMBER = 4;
    private long lastLoginDttm_;
    /**
     * <code>required int64 lastLoginDttm = 4;</code>
     */
    public boolean hasLastLoginDttm() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required int64 lastLoginDttm = 4;</code>
     */
    public long getLastLoginDttm() {
      return lastLoginDttm_;
    }

    private void initFields() {
      account_ = "";
      userId_ = 0;
      createDttm_ = 0L;
      lastLoginDttm_ = 0L;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasAccount()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUserId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasCreateDttm()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasLastLoginDttm()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getAccountBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, userId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt64(3, createDttm_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt64(4, lastLoginDttm_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getAccountBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, userId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(3, createDttm_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(4, lastLoginDttm_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static qxmobile.protobuf.AccountProtos.Account parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static qxmobile.protobuf.AccountProtos.Account parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static qxmobile.protobuf.AccountProtos.Account parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static qxmobile.protobuf.AccountProtos.Account parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static qxmobile.protobuf.AccountProtos.Account parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static qxmobile.protobuf.AccountProtos.Account parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static qxmobile.protobuf.AccountProtos.Account parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static qxmobile.protobuf.AccountProtos.Account parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static qxmobile.protobuf.AccountProtos.Account parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static qxmobile.protobuf.AccountProtos.Account parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(qxmobile.protobuf.AccountProtos.Account prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code qxmobile.protobuf.Account}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements qxmobile.protobuf.AccountProtos.AccountOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return qxmobile.protobuf.AccountProtos.internal_static_qxmobile_protobuf_Account_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return qxmobile.protobuf.AccountProtos.internal_static_qxmobile_protobuf_Account_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                qxmobile.protobuf.AccountProtos.Account.class, qxmobile.protobuf.AccountProtos.Account.Builder.class);
      }

      // Construct using qxmobile.protobuf.AccountProtos.Account.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        account_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        userId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        createDttm_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000004);
        lastLoginDttm_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return qxmobile.protobuf.AccountProtos.internal_static_qxmobile_protobuf_Account_descriptor;
      }

      public qxmobile.protobuf.AccountProtos.Account getDefaultInstanceForType() {
        return qxmobile.protobuf.AccountProtos.Account.getDefaultInstance();
      }

      public qxmobile.protobuf.AccountProtos.Account build() {
        qxmobile.protobuf.AccountProtos.Account result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public qxmobile.protobuf.AccountProtos.Account buildPartial() {
        qxmobile.protobuf.AccountProtos.Account result = new qxmobile.protobuf.AccountProtos.Account(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.account_ = account_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.userId_ = userId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.createDttm_ = createDttm_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.lastLoginDttm_ = lastLoginDttm_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof qxmobile.protobuf.AccountProtos.Account) {
          return mergeFrom((qxmobile.protobuf.AccountProtos.Account)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(qxmobile.protobuf.AccountProtos.Account other) {
        if (other == qxmobile.protobuf.AccountProtos.Account.getDefaultInstance()) return this;
        if (other.hasAccount()) {
          bitField0_ |= 0x00000001;
          account_ = other.account_;
          onChanged();
        }
        if (other.hasUserId()) {
          setUserId(other.getUserId());
        }
        if (other.hasCreateDttm()) {
          setCreateDttm(other.getCreateDttm());
        }
        if (other.hasLastLoginDttm()) {
          setLastLoginDttm(other.getLastLoginDttm());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasAccount()) {
          
          return false;
        }
        if (!hasUserId()) {
          
          return false;
        }
        if (!hasCreateDttm()) {
          
          return false;
        }
        if (!hasLastLoginDttm()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        qxmobile.protobuf.AccountProtos.Account parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (qxmobile.protobuf.AccountProtos.Account) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required string account = 1;
      private java.lang.Object account_ = "";
      /**
       * <code>required string account = 1;</code>
       */
      public boolean hasAccount() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string account = 1;</code>
       */
      public java.lang.String getAccount() {
        java.lang.Object ref = account_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          account_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string account = 1;</code>
       */
      public com.google.protobuf.ByteString
          getAccountBytes() {
        java.lang.Object ref = account_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          account_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string account = 1;</code>
       */
      public Builder setAccount(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        account_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string account = 1;</code>
       */
      public Builder clearAccount() {
        bitField0_ = (bitField0_ & ~0x00000001);
        account_ = getDefaultInstance().getAccount();
        onChanged();
        return this;
      }
      /**
       * <code>required string account = 1;</code>
       */
      public Builder setAccountBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        account_ = value;
        onChanged();
        return this;
      }

      // required int32 userId = 2;
      private int userId_ ;
      /**
       * <code>required int32 userId = 2;</code>
       */
      public boolean hasUserId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 userId = 2;</code>
       */
      public int getUserId() {
        return userId_;
      }
      /**
       * <code>required int32 userId = 2;</code>
       */
      public Builder setUserId(int value) {
        bitField0_ |= 0x00000002;
        userId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 userId = 2;</code>
       */
      public Builder clearUserId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        userId_ = 0;
        onChanged();
        return this;
      }

      // required int64 createDttm = 3;
      private long createDttm_ ;
      /**
       * <code>required int64 createDttm = 3;</code>
       */
      public boolean hasCreateDttm() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int64 createDttm = 3;</code>
       */
      public long getCreateDttm() {
        return createDttm_;
      }
      /**
       * <code>required int64 createDttm = 3;</code>
       */
      public Builder setCreateDttm(long value) {
        bitField0_ |= 0x00000004;
        createDttm_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 createDttm = 3;</code>
       */
      public Builder clearCreateDttm() {
        bitField0_ = (bitField0_ & ~0x00000004);
        createDttm_ = 0L;
        onChanged();
        return this;
      }

      // required int64 lastLoginDttm = 4;
      private long lastLoginDttm_ ;
      /**
       * <code>required int64 lastLoginDttm = 4;</code>
       */
      public boolean hasLastLoginDttm() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required int64 lastLoginDttm = 4;</code>
       */
      public long getLastLoginDttm() {
        return lastLoginDttm_;
      }
      /**
       * <code>required int64 lastLoginDttm = 4;</code>
       */
      public Builder setLastLoginDttm(long value) {
        bitField0_ |= 0x00000008;
        lastLoginDttm_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 lastLoginDttm = 4;</code>
       */
      public Builder clearLastLoginDttm() {
        bitField0_ = (bitField0_ & ~0x00000008);
        lastLoginDttm_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:qxmobile.protobuf.Account)
    }

    static {
      defaultInstance = new Account(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:qxmobile.protobuf.Account)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_qxmobile_protobuf_Account_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_qxmobile_protobuf_Account_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\raccount.proto\022\021qxmobile.protobuf\"U\n\007Ac" +
      "count\022\017\n\007account\030\001 \002(\t\022\016\n\006userId\030\002 \002(\005\022\022" +
      "\n\ncreateDttm\030\003 \002(\003\022\025\n\rlastLoginDttm\030\004 \002(" +
      "\003B\017B\rAccountProtos"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_qxmobile_protobuf_Account_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_qxmobile_protobuf_Account_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_qxmobile_protobuf_Account_descriptor,
              new java.lang.String[] { "Account", "UserId", "CreateDttm", "LastLoginDttm", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
